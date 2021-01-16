package com.example.kafkafetchdemo;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@RequiredArgsConstructor
public class DemoKafkaProducer {

  private final KafkaTemplate<Object, Object> kafkaTemplate;
  private final DemoProperties demoProperties;

  private static final String LARGE = resourceAsString("large.json");
  private static final String SMALL = resourceAsString("small.json");

  public void produceEvents(final int count, final boolean large) {
    log.info("Produce {} events", count);
    for (int i = 0; i < count; i++) {
      kafkaTemplate.send(demoProperties.getProducerTopic(), large ? LARGE : SMALL);
    }
    log.info("Finished production of {} events", count);
  }

  @SneakyThrows
  public static String resourceAsString(String resource) {
    final ResourceLoader resourceLoader = new DefaultResourceLoader();
    final Resource springResource = resourceLoader.getResource(resource);
    return StreamUtils
            .copyToString(springResource.getInputStream(), StandardCharsets.UTF_8)
            .replace("\r\n", "\n");

  }
}
