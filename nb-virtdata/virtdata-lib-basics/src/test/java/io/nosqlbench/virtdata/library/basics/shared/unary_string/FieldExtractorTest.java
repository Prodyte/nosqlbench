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

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FieldExtractorTest {

    @Test
    public void testSubset() {
        FieldExtractor fieldExtractor = new FieldExtractor("|,2,3,5");
        assertThat(fieldExtractor.apply("one|two|three|four|five|six")).isEqualTo("two|three|five");
    }

    @Test
    public void testUnderrun() {
        FieldExtractor fieldExtractor = new FieldExtractor("|,2,3,5");
        assertThat(fieldExtractor.apply("one|two")).isEqualTo("ERROR-UNDERRUN in FieldExtractor");

    }
}
