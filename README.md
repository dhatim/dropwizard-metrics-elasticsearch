# Dropwizard Metrics Support for Elastic Search

THis modules enables a [dropwizard][dw] application to send
[metrics][dwm] to an [elasticsearch][e] instance.

## What about [Metrics Elasticsearch Reporter][mer]? Doesn't it do just that?

[No, it doesn't][dwi]. As outlined [here][merc], it needs you to write
some code in your application. This module adds the ability to perform
that task through your dropwizard application yaml configuration. In
fact, under th hood it actually makes use of
[Metrics Elasticsearch Reporter][mer] to perform the work.

## How to

- add this jar as a dependency to the dropwizard app:

```xml
<dependencies>
    ../..
    <dependency>
        <groupId>com.dhatim.io.dropwizard</groupId>
        <artifactId>dropwizard-metrics-elasticsearch</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </dependency>
    ../..
</dependencies>
```

- enable the metrics reporter through the configuration:

```yaml
metrics:
  reporters:
    - type: elasticsearch
```

and you're good to go.

## configuration

The configuration is quite limited right now:

- host: the elasticsearch server hostname
- port: the port elasticsearch listens on

[dw]: http://www.dropwizard.io
[dwm]: http://metrics.dropwizard.io
[e]: https://www.elastic.co/products/elasticsearch
[mer]: https://github.com/elastic/elasticsearch-metrics-reporter-java
[dwi]: https://github.com/dropwizard/dropwizard/issues/1277
[merc]: https://github.com/elastic/elasticsearch-metrics-reporter-java#configuration
