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
import io.milvus.grpc.DescribeCollectionResponse;
import io.milvus.param.R;
import io.milvus.param.collection.DescribeCollectionParam;

public class MilvusDescribeCollectionOp extends MilvusBaseOp<DescribeCollectionParam> {
    public MilvusDescribeCollectionOp(MilvusServiceClient client, DescribeCollectionParam request) {
        super(client, request);
    }

    @Override
    public Object applyOp(long value) {
        R<DescribeCollectionResponse> describeCollectionResponseR = client.describeCollection(request);
        return describeCollectionResponseR;
    }
}
