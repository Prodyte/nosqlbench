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

package io.nosqlbench.virtdata.library.basics.shared.stateful.from_long;

import io.nosqlbench.virtdata.api.annotations.Categories;
import io.nosqlbench.virtdata.api.annotations.Category;
import io.nosqlbench.virtdata.api.annotations.Example;
import io.nosqlbench.virtdata.api.annotations.ThreadSafeMapper;
import io.nosqlbench.virtdata.library.basics.core.threadstate.SharedState;

import java.util.HashMap;
import java.util.function.LongFunction;

/**
 * Save a value to a named thread-local variable, where the variable
 * name is fixed or a generated variable name from a provided function.
 * Note that the input type is not that suitable for constructing names,
 * so this is more likely to be used in an indirect naming pattern like
 * SaveFloat(Load('id'))
 */
@Categories(Category.state)
@ThreadSafeMapper
public class SaveFloat implements LongFunction<Float> {

    private final String name;
    private final LongFunction<Object> nameFunc;

    @Example({"SaveFloat('foo')","save the current float value to a named variable in this thread."})
    public SaveFloat(String name) {
        this.name = name;
        this.nameFunc=null;
    }

    @Example({"SaveFloat(NumberNameToString())","save the current float value to a named variable in this thread" +
            ", where the variable name is provided by a function."})
    public SaveFloat(LongFunction<Object> nameFunc) {
        this.name=null;
        this.nameFunc = nameFunc;
    }

    @Override
    public Float apply(long value) {
        HashMap<String, Object> map = SharedState.tl_ObjectMap.get();
        String varname=(nameFunc!=null) ? String.valueOf(nameFunc.apply(value)) : name;
        map.put(varname,value);
        return (float) value;
    }
}
