package com.example.kafkafetchdemo;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class DemoConfiguration {

  private final DemoProperties demoProperties;
  private final KafkaAdmin kafkaAdmin;

  @Bean
  public ApplicationRunner runner() {
    return args -> {
      final AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties());
      final Set<String> existingTopics = adminClient.listTopics().names().get();
      final List<NewTopic> newTopics = demoProperties.getTopics().stream()
              .filter(topic -> !existingTopics.contains(topic))
              .map(topic -> new NewTopic(topic, 1, (short) 1))
              .collect(Collectors.toList());
      if (!newTopics.isEmpty()) {
        adminClient.createTopics(newTopics).all().get();
      }
    };
  }
}
