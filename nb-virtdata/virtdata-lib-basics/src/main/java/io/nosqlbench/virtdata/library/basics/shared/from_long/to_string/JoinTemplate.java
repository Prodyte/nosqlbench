/*
 * Copyright (c) 2022 nosqlbench
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

package io.nosqlbench.virtdata.library.basics.shared.from_long.to_string;

import io.nosqlbench.virtdata.api.annotations.Categories;
import io.nosqlbench.virtdata.api.annotations.Category;
import io.nosqlbench.virtdata.api.annotations.Example;
import io.nosqlbench.virtdata.api.annotations.ThreadSafeMapper;

import java.util.function.LongFunction;
import java.util.function.LongUnaryOperator;

/**
 * Combine the result of the specified functions together with the
 * specified delimiter and optional prefix and suffix.
 */
@ThreadSafeMapper
@Categories({Category.general})
public class JoinTemplate extends Template implements LongFunction<String>  {

    @Example({"JoinTemplate('--',NumberNameToString(),NumberNameToString())","create values like `one--one`, `two-two`, ..."})
    public JoinTemplate(String delimiter, LongFunction<?>... funcs) {
        super(templateFor("",delimiter,"",funcs), funcs);
    }

    @Example({"JoinTemplate('{',',','}',NumberNameToString(),LastNames())", "create values like '{one,Farrel}', '{two,Haskell}', ..."})
    public JoinTemplate(String prefix, String delimiter, String suffix, LongFunction<?>... funcs) {
        super(templateFor(prefix,delimiter,suffix,funcs), funcs);
    }

    @Example({"JoinTemplate(Add(3),'<',';','>',NumberNameToString(),NumberNameToString(),NumberNameToString())",
    "create values like '<zero;three,six>', '<one;four,seven>', ..."})
    public JoinTemplate(LongUnaryOperator iterop, String prefix, String delimiter, String suffix, LongFunction<?>... funcs) {
        super(iterop, templateFor(prefix,delimiter,suffix,funcs), funcs);

    }
    private static String templateFor(String prefix, String delimiter, String suffix, LongFunction<?>... funcs) {
        StringBuilder sb=new StringBuilder();
        sb.append(prefix);
        for (int i = 0; i < funcs.length; i++) {
            sb.append("{}");
            sb.append(delimiter);
        }
        sb.setLength(sb.length()-delimiter.length());
        sb.append(suffix);
        return sb.toString();
    }

}
