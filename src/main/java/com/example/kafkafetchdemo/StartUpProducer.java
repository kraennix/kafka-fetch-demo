package com.example.kafkafetchdemo;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("produce")
@RequiredArgsConstructor
public class StartUpProducer implements CommandLineRunner {

  private final DemoProperties demoProperties;

  private final DemoKafkaProducer demoKafkaProducer;

  @Override
  public void run(String... args) {
    demoKafkaProducer.produceEvents(demoProperties.getNumberOfEvents(), demoProperties.isLarge());
  }
}
