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

package io.nosqlbench.virtdata.library.basics.shared.from_double.to_double;

import io.nosqlbench.virtdata.api.annotations.Categories;
import io.nosqlbench.virtdata.api.annotations.Category;
import io.nosqlbench.virtdata.api.annotations.Example;
import io.nosqlbench.virtdata.api.annotations.ThreadSafeMapper;

import java.util.function.DoubleUnaryOperator;

@ThreadSafeMapper
@Categories({Category.general})
public class Clamp implements DoubleUnaryOperator {

    private final double min;
    private final double max;

    @Example({"Clamp(1.0D,9.0D)","clamp output values between the range [1.0D, 9.0D], inclusive"})
    public Clamp(double min, double max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public double applyAsDouble(double operand) {
        return Double.min(max,Double.max(min,operand));
    }
}
