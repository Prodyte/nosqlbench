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

package io.nosqlbench.adapter.opensearch.ops;

import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch.core.SearchRequest;
import org.opensearch.client.opensearch.core.SearchResponse;

public class AOSKnnSearchOp extends AOSBaseOp {
    private final SearchRequest rq;
    private final Class<?> doctype;

    public AOSKnnSearchOp(OpenSearchClient client, SearchRequest rq, Class<?> doctype) {
        super(client);
        this.rq = rq;
        this.doctype = doctype;
    }

    @Override
    public Object applyOp(long value) throws Exception {
        SearchResponse response = client.search(rq, doctype);
        return response;
    }

}
