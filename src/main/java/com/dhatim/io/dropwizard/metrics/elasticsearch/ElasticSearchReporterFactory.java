package com.dhatim.io.dropwizard.metrics.elasticsearch;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.dropwizard.metrics.BaseReporterFactory;
import java.io.IOException;
import org.elasticsearch.metrics.ElasticsearchReporter;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

@JsonTypeName("elasticsearch")
public class ElasticSearchReporterFactory extends BaseReporterFactory {

    @NotEmpty
    private String host = "localhost";

    @Range(min = 1, max = 49151)
    private int port = 2900;

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public ScheduledReporter build(MetricRegistry registry) {
        try {
            return ElasticsearchReporter.forRegistry(registry)
                    .hosts(host + ":" + port)
                    .build();
        } catch (IOException e) {
            throw new RuntimeException("can't build elasticsearch reporter", e);
        }
    }
}
