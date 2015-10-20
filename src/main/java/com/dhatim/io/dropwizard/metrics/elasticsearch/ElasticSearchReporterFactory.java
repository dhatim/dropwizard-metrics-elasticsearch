/**
 * Copyright 2015 Dhatim
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.dhatim.io.dropwizard.metrics.elasticsearch;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.dropwizard.metrics.BaseReporterFactory;
import java.io.IOException;
import java.util.Arrays;
import org.elasticsearch.metrics.ElasticsearchReporter;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

@JsonTypeName("elasticsearch")
public class ElasticSearchReporterFactory extends BaseReporterFactory {

    public static class Server {

        @NotEmpty
        public String host = "localhost";

        @Range(min = 1, max = 49151)
        public int port = 9200;

        public Server() {
        }
        
        public Server(String host, int port) {
            this.host = host;
            this.port = port;
        }
    }

    public Server[] servers = new Server[]{ new Server("localhost", 9200) };

    public String index = "metrics";

    @Override
    public ScheduledReporter build(MetricRegistry registry) {
        try {
            String[] hosts = Arrays.stream(servers)
                    .map(s -> s.host + ":" + s.port)
                    .toArray(String[]::new);

            return ElasticsearchReporter.forRegistry(registry)
                    .hosts(hosts)
                    .index(index)
                    .build();
        } catch (IOException e) {
            throw new RuntimeException("can't build elasticsearch reporter", e);
        }
    }
}
