/*
 * Copyright (c) 2022-2023 nosqlbench
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

package io.nosqlbench.adapter.dynamodb;

import io.nosqlbench.adapter.dynamodb.optypes.DynamoDBOp;
import io.nosqlbench.adapters.api.activityimpl.OpMapper;
import io.nosqlbench.adapters.api.activityimpl.uniform.BaseDriverAdapter;
import io.nosqlbench.adapters.api.activityimpl.uniform.ConcurrentSpaceCache;
import io.nosqlbench.adapters.api.activityimpl.uniform.DriverAdapter;
import io.nosqlbench.adapters.api.activityimpl.uniform.StringDriverSpaceCache;
import io.nosqlbench.nb.api.labels.NBLabels;
import io.nosqlbench.nb.api.components.core.NBComponent;
import io.nosqlbench.nb.annotations.Maturity;
import io.nosqlbench.nb.annotations.Service;
import io.nosqlbench.nb.api.config.standard.NBConfigModel;
import io.nosqlbench.nb.api.config.standard.NBConfiguration;

import java.util.function.Function;
import java.util.function.LongFunction;

@Service(value = DriverAdapter.class, selector = "dynamodb", maturity = Maturity.Experimental)
public class DynamoDBDriverAdapter extends BaseDriverAdapter<DynamoDBOp, DynamoDBSpace> {

    public DynamoDBDriverAdapter(NBComponent parentComponent, NBLabels labels) {
        super(parentComponent, labels);
    }

    @Override
    public OpMapper<DynamoDBOp,DynamoDBSpace> getOpMapper() {
        NBConfiguration adapterConfig = getConfiguration();
        return new DynamoDBOpMapper(this, adapterConfig, getSpaceCache());
    }

    @Override
    public LongFunction<DynamoDBSpace> getSpaceInitializer(NBConfiguration cfg) {
        return (s) -> new DynamoDBSpace(this, s,cfg);
    }

    @Override
    public NBConfigModel getConfigModel() {
        return super.getConfigModel().add(DynamoDBSpace.getConfigModel());
    }

}
