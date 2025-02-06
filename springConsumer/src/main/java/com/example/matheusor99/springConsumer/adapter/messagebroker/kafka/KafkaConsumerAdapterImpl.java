package com.example.matheusor99.springConsumer.adapter.messagebroker.kafka;

import br.com.matheusor99.springProducer.People;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumerAdapterImpl implements KafkaConsumerAdapter {

    @KafkaListener(
            topics = "${kafka.topic-name-consumer}",
            groupId = "${kafka.group-id}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consumePeople(final ConsumerRecord<String, People> record, final Acknowledgment ack) {
        try {
            final var people = record.value();
            log.info("Pessoa consumida: {}", people.toString());
            ack.acknowledge();
        } catch (Exception e) {
            log.error("Erro ao processar mensagem do people-topic", e);
        }
    }
}
