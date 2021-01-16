package com.example.kafkafetchdemo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "demo")
public class DemoProperties {

  private int numberOfEvents;
  private String topicPattern;
  private Set<String> topics;
  private String producerTopic;
  private boolean large;
}
