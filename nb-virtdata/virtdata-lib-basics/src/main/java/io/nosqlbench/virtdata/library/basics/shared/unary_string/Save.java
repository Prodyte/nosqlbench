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

package io.nosqlbench.virtdata.library.basics.shared.unary_string;

import io.nosqlbench.virtdata.api.annotations.Categories;
import io.nosqlbench.virtdata.api.annotations.Category;
import io.nosqlbench.virtdata.api.annotations.Example;
import io.nosqlbench.virtdata.api.annotations.ThreadSafeMapper;
import io.nosqlbench.virtdata.library.basics.core.threadstate.SharedState;

import java.util.function.Function;

@ThreadSafeMapper
@Categories({Category.state})
public class Save implements Function<String,String> {

    private final String name;
    private final Function<Object,Object> nameFunc;

    @Example({"Save('foo')","save the current String value to the name 'foo' in this thread"})
    public Save(String name) {
        this.name = name;
        this.nameFunc=null;
    }

    @Example({"Save(NumberNameToString())","save the current String value to a named variable in" +
            " this thread, where the variable name is provided by a function"})
    public Save(Function<Object,Object> nameFunc) {
        this.name = null;
        this.nameFunc=nameFunc;
    }

    @Override
    public String apply(String s) {
        SharedState.tl_ObjectMap.get().put(name,s);
        return s;
    }

}
