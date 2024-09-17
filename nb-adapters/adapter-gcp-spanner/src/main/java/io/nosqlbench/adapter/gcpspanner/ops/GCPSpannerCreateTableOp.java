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
 *
 */

package io.nosqlbench.adapter.gcpspanner.ops;

import com.google.api.gax.longrunning.OperationFuture;
import com.google.cloud.spanner.*;
import com.google.common.collect.ImmutableList;
import com.google.spanner.admin.database.v1.UpdateDatabaseDdlMetadata;

public class GCPSpannerCreateTableOp extends GCPSpannerBaseOp<Long> {
    private final String createTableStatement;
    private final DatabaseAdminClient dbAdminClient;
    private final Database db;

    public GCPSpannerCreateTableOp(Spanner searchIndexClient, Long requestParam, String createTableStatement,
                                   DatabaseAdminClient dbAdminClient, Database db) {
        super(searchIndexClient, requestParam);
        this.createTableStatement = createTableStatement;
        this.dbAdminClient = dbAdminClient;
        this.db = db;
    }

    @Override
    public Object applyOp(long value) {
        OperationFuture<Void, UpdateDatabaseDdlMetadata> operation = dbAdminClient.updateDatabaseDdl(
            db,
            ImmutableList.of(createTableStatement),
            null);
        try {
            return operation.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
