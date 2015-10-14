package com.dhatim.io.dropwizard.metrics.elasticsearch;

import io.dropwizard.jackson.DiscoverableSubtypeResolver;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class ElasticSearchReporterFactoryTest {

    @Test
    public void isDiscoverable() throws Exception {
        assertThat(new DiscoverableSubtypeResolver().getDiscoveredSubtypes())
                .contains(ElasticSearchReporterFactory.class);
    }
}
