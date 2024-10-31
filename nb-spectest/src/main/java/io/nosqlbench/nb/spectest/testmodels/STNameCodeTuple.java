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

package io.nosqlbench.nb.spectest.testmodels;

import com.vladsch.flexmark.util.ast.Node;

import java.nio.file.Path;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * A {@link STNameCodeTuple} is a set of metadata that describes a test target from a
 * test specification file, in terms of {@link Node} sequences and context.
 * It contains naming and locating information as well as the document nodes containing
 * the target element.
 */
public final class STNameCodeTuple {

    private final String description;
    private final Path path;
    private final int line;
    private final Node refnode;
    public CharSequence info;
    public CharSequence text;

    public STNameCodeTuple(Supplier<CharSequence> desc, Node infoNode, Node dataNode, Path path) {
        this.description = desc.get().toString();
        this.info = infoNode.getChars();
        this.text = dataNode.getFirstChild().getChars();
        this.line = dataNode.getFirstChild().getLineNumber();
        this.path = path;
        this.refnode = dataNode;
    }

    public String getDesc() {
        return description;
    }

    public Path getPath() {
        return path;
    }

    public int getLine() {
        return line;
    }

    public Node getRefNode() {
        return refnode;
    }

    /**
     * Provide the logical path of the file being examined in this test set.
     * If the system properties indicate that the test is being run from within intellij,
     * the path will be relativized from the next module level up to allow for hot linking
     * directly to files.
     * @return A useful relative path to the file being tested
     */
    public String getLocationRef() {
        boolean inij = System.getProperty("sun.java.command","").toLowerCase(Locale.ROOT).contains("intellij");
        Path vcwd = Path.of(".").toAbsolutePath().normalize();
        vcwd = inij ? vcwd.getParent().normalize() : vcwd;
        Path relpath = vcwd.relativize(this.path.toAbsolutePath());
        if (inij) {
            relpath = Path.of(relpath.toString().replace("target/classes/","src/main/resources/"));
        }
        return "\t at (" + relpath + ":" + this.getLine() + ")";
    }

    @Override
    public String toString() {
        return this.getDesc();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        STNameCodeTuple that = (STNameCodeTuple) o;

        if (line != that.line) return false;
        if (!Objects.equals(description, that.description)) return false;
        if (!Objects.equals(path, that.path)) return false;
        if (!Objects.equals(refnode, that.refnode)) return false;
        if (!Objects.equals(info, that.info)) return false;
        return Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + line;
        result = 31 * result + (refnode != null ? refnode.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
