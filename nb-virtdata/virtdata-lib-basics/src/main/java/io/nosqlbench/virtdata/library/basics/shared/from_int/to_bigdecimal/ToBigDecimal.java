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

package io.nosqlbench.virtdata.library.basics.shared.from_int.to_bigdecimal;

import io.nosqlbench.virtdata.api.annotations.Categories;
import io.nosqlbench.virtdata.api.annotations.Category;
import io.nosqlbench.virtdata.api.annotations.Example;
import io.nosqlbench.virtdata.api.annotations.ThreadSafeMapper;
import io.nosqlbench.virtdata.library.basics.shared.util.MathContextReader;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.function.DoubleFunction;
import java.util.function.IntFunction;

@ThreadSafeMapper
@Categories(Category.conversion)
public class ToBigDecimal implements IntFunction<BigDecimal> {

    private final MathContext mathContext;

    @Example({"ToBigDecimal()", "Convert all int values to BigDecimal values with no limits (using MathContext" +
        ".UNLIMITED)"})
    public ToBigDecimal() {
        this.mathContext = MathContext.UNLIMITED;
    }

    /**
     * Convert all input values to BigDecimal values with a specific MathContext.
     * The value for context can be one of UNLIMITED,
     * DECIMAL32, DECIMAL64, DECIMAL128, or any valid configuration supported by
     * {@link MathContext#MathContext(String)}, such as {@code "precision=32 roundingMode=CEILING"}.
     * In the latter form, roundingMode can be any valid value for {@link RoundingMode}, like
     * UP, DOWN, CEILING, FLOOR, HALF_UP, HALF_DOWN, HALF_EVEN, or UNNECESSARY.
     */
    @Example({"ToBigDecimal('DECIMAL32')", "IEEE 754R Decimal32 format, 7 digits, HALF_EVEN"})
    @Example({"ToBigDecimal('DECIMAL64'),", "IEEE 754R Decimal64 format, 16 digits, HALF_EVEN"})
    @Example({"ToBigDecimal('DECIMAL128')", "IEEE 754R Decimal128 format, 34 digits, HALF_EVEN"})
    @Example({"ToBigDecimal('UNLIMITED')", "unlimited precision, HALF_UP"})
    @Example({"ToBigDecimal('precision=17 roundingMode=UNNECESSARY')", "Custom precision with no rounding performed"})
    public ToBigDecimal(String context) {
        this.mathContext = MathContextReader.getMathContext(context);
    }

    @Override
    public BigDecimal apply(int value) {
        return new BigDecimal(value, mathContext);
    }
}
