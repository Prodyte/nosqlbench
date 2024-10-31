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

package io.nosqlbench.adapter.milvus.ops;

import io.milvus.client.MilvusServiceClient;
import io.milvus.param.control.GetCompactionPlansParam;
import io.milvus.param.control.GetCompactionStateParam;

public class MilvusGetCompactionStateWithPlansOp extends MilvusBaseOp<GetCompactionPlansParam> {
    public MilvusGetCompactionStateWithPlansOp(MilvusServiceClient client, GetCompactionPlansParam request) {
        super(client, request);
    }

    @Override
    public Object applyOp(long value) {
        return client.getCompactionStateWithPlans(request);
    }
}
