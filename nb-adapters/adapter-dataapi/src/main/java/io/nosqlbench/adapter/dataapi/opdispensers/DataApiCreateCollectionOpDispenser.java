/*
 * Copyright (c) nosqlbench
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

package io.nosqlbench.adapter.dataapi.opdispensers;

import com.datastax.astra.client.model.CollectionOptions;
import com.datastax.astra.client.model.SimilarityMetric;
import io.nosqlbench.adapter.dataapi.DataApiDriverAdapter;
import io.nosqlbench.adapter.dataapi.ops.DataApiBaseOp;
import io.nosqlbench.adapter.dataapi.ops.DataApiCreateCollectionOp;
import io.nosqlbench.adapters.api.templating.ParsedOp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.function.LongFunction;

public class DataApiCreateCollectionOpDispenser extends DataApiOpDispenser {
    private static final Logger logger = LogManager.getLogger(DataApiCreateCollectionOpDispenser.class);
    private final LongFunction<DataApiCreateCollectionOp> opFunction;

    public DataApiCreateCollectionOpDispenser(DataApiDriverAdapter adapter, ParsedOp op, LongFunction<String> targetFunction) {
        super(adapter, op, targetFunction);
        this.opFunction = createOpFunction(op);
    }

    private LongFunction<DataApiCreateCollectionOp> createOpFunction(ParsedOp op) {
        return (l) -> {
            DataApiCreateCollectionOp dataApiCreateCollectionOp =
                new DataApiCreateCollectionOp(
                    spaceFunction.apply(l).getDatabase(),
                    targetFunction.apply(l),
                    this.getCollectionOptionsFromOp(op, l)
                );

            return dataApiCreateCollectionOp;
        };
    }

    @Override
    public DataApiBaseOp getOp(long value) {
        return opFunction.apply(value);
    }


}
