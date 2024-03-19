/*
 * Copyright (c) 2024 nosqlbench
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.nosqlbench.adapter.milvus.opdispensers;

import io.milvus.client.MilvusServiceClient;
import io.milvus.param.index.CreateIndexParam;
import io.nosqlbench.adapter.milvus.MilvusDriverAdapter;
import io.nosqlbench.adapter.milvus.ops.MilvusBaseOp;
import io.nosqlbench.adapter.milvus.ops.MilvusCreateIndexOp;
import io.nosqlbench.adapters.api.templating.ParsedOp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.LongFunction;

public class MilvusCreateIndexOpDispenser extends MilvusBaseOpDispenser<CreateIndexParam> {
    private static final Logger logger = LogManager.getLogger(MilvusCreateIndexOpDispenser.class);

    /**
     * Create a new MilvusCreateIndexOpDispenser subclassed from {@link MilvusBaseOpDispenser}.
     *
     * @param adapter
     *     The associated {@link MilvusDriverAdapter}
     * @param op
     *     The {@link ParsedOp} encapsulating the activity for this cycle
     * @param targetFunction
     *     A LongFunction that returns the specified Milvus Index for this Op
     */
    public MilvusCreateIndexOpDispenser(
        MilvusDriverAdapter adapter,
        ParsedOp op,
        LongFunction<String> targetFunction
    ) {
        super(adapter, op, targetFunction);
    }

    @Override
    public LongFunction<CreateIndexParam> getParamFunc(LongFunction<MilvusServiceClient> clientF, ParsedOp op, LongFunction<String> targetF) {
        LongFunction<CreateIndexParam.Builder> bF =
            l -> CreateIndexParam.newBuilder().withIndexName(targetF.apply(l));

        bF = op.enhanceFunc(bF, "collection_name", String.class, CreateIndexParam.Builder::withCollectionName);
        bF = op.enhanceFunc(bF, "field_name", String.class, CreateIndexParam.Builder::withFieldName);
        bF = op.enhanceFunc(bF, "index_type", String.class, CreateIndexParam.Builder::withFieldName);
        bF = op.enhanceFunc(bF, "metric_type", String.class, CreateIndexParam.Builder::withFieldName);
        bF = op.enhanceFuncOptionally(bF, "extra_param", String.class, CreateIndexParam.Builder::withFieldName);
        bF = op.enhanceFuncOptionally(bF, "sync_mode", Boolean.class, CreateIndexParam.Builder::withSyncMode);
        bF = op.enhanceFuncOptionally(bF, "sync_waiting_interval", Long.class, CreateIndexParam.Builder::withSyncWaitingInterval);
        bF = op.enhanceFuncOptionally(bF, "sync_waiting_timeout", Long.class, CreateIndexParam.Builder::withSyncWaitingTimeout);
        LongFunction<CreateIndexParam.Builder> finalBF1 = bF;
        return l -> finalBF1.apply(l).build();
    }

    @Override
    public LongFunction<MilvusBaseOp<CreateIndexParam>> createOpFunc(LongFunction<CreateIndexParam> paramF, LongFunction<MilvusServiceClient> clientF, ParsedOp op, LongFunction<String> targetF) {
        return l -> new MilvusCreateIndexOp(clientF.apply(l), paramF.apply(l));
    }
}
