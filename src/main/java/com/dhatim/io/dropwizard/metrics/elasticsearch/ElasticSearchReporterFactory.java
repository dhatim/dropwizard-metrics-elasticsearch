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
        public int port = 2900;

        public Server() {
        }
        
        public Server(String host, int port) {
            this.host = host;
            this.port = port;
        }
    }

    public Server[] servers = new Server[]{ new Server("localhost", 9200) };

    @Override
    public ScheduledReporter build(MetricRegistry registry) {
        try {
            String[] hosts = Arrays.stream(servers)
                    .map(s -> s.host + ":" + s.port)
                    .toArray(String[]::new);

            return ElasticsearchReporter.forRegistry(registry)
                    .hosts(hosts)
                    .build();
        } catch (IOException e) {
            throw new RuntimeException("can't build elasticsearch reporter", e);
        }
    }
}
