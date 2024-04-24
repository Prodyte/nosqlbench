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

package io.nosqlbench.adapter.opensearch.ops;

import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch.core.DeleteRequest;

import java.io.IOException;

public class AOSDeleteOp extends AOSBaseOp {
    private final DeleteRequest rq;

    public AOSDeleteOp(OpenSearchClient client, DeleteRequest rq) {
        super(client);
        this.rq = rq;
    }

    public Object applyOp(long value) throws IOException {
        return client.delete(rq);
    }
}
