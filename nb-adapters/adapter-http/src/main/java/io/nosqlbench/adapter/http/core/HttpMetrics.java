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

package io.nosqlbench.adapter.http.core;

import com.codahale.metrics.Histogram;
import io.nosqlbench.adapter.http.HttpDriverAdapter;
import io.nosqlbench.nb.api.components.core.NBComponentProps;
import io.nosqlbench.nb.api.engine.metrics.instruments.MetricCategory;
import io.nosqlbench.nb.api.labels.NBLabeledElement;
import io.nosqlbench.nb.api.labels.NBLabels;

public class HttpMetrics implements NBLabeledElement {
    private final HttpDriverAdapter parentAdapter;
    final Histogram statusCodeHistogram;

    public HttpMetrics(HttpDriverAdapter parentAdapter) {
        this.parentAdapter = parentAdapter;
        statusCodeHistogram = parentAdapter.create().histogram(
            "statuscode",
            Integer.parseInt(parentAdapter.getComponentProp(NBComponentProps.HDRDIGITS).orElse("3")),
            MetricCategory.Payload,
            "A histogram of status codes received by the HTTP client"
        );
    }

    public String getName() {
        return parentAdapter.getAdapterName() + "-metrics";
    }

    @Override
    public NBLabels getLabels() {
        return parentAdapter.getLabels();
    }
}
