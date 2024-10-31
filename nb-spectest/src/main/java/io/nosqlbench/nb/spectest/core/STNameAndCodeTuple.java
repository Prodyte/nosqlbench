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

package io.nosqlbench.nb.spectest.core;

import io.nosqlbench.nb.spectest.testmodels.STNodeReference;

import java.nio.file.Path;

public record STNameAndCodeTuple(
    STNode nameNode,
    STNode dataNode
) implements STNodeReference {

    public String getDesc() {
        return nameNode.getDesc();
    }
    public String getName() {
        return nameNode.getText();
    }

    public String getData() {
        return dataNode.getText();
    }

    @Override
    public Path getPath() {
        return dataNode.getPath();
    }

    @Override
    public int getLineNumber() {
        return dataNode.getLine();
    }
}
