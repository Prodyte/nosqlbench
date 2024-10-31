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

package io.nosqlbench.nb.api.components.core;

import io.nosqlbench.nb.api.config.standard.TestComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

class NBComponentScaffoldingTest {

    private final static Logger logger = LogManager.getLogger(NBComponentScaffoldingTest.class);
    @Test
    public void testBasicLayeringTeardown() {
        TestComponent root = new TestComponent("root","root");
        TestComponent a1 = new TestComponent(root,"a1","a1") {
            @Override
            protected void teardown() {
                logger.debug("tearing down " + description());
            }
        };
        TestComponent b2 = new TestComponent(a1,"b2","b2") {
            @Override
            protected void teardown() {
                logger.debug("tearing down " + description());
            }
        };

        root.close();

    }

}
