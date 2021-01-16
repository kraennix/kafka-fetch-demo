package com.example.kafkafetchdemo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Profile("consume")
@Slf4j
@Component
@RequiredArgsConstructor
public class DemoKafkaListener {

  public static final String LISTENER_ID = "DEMO_LISTENER";

  @KafkaListener(id = LISTENER_ID, idIsGroup = false, topicPattern = "#{demoProperties.getTopicPattern()}")
  public void onEvent(Acknowledgment acknowledgment, ConsumerRecord<byte[], String> record) {
    log.info("Received record on topic {}, partition {} and offset {}",
            record.topic(),
            record.partition(),
            record.offset());

    acknowledgment.acknowledge();
  }
}
