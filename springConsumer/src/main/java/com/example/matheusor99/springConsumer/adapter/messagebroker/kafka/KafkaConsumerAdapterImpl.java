package com.example.matheusor99.springConsumer.adapter.messagebroker.kafka;

import br.com.matheusor99.springConsumer.People;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumerAdapterImpl implements KafkaConsumerAdapter {

    @Override
    @KafkaListener(topics = "${kafka.topic-name-consumer}", groupId = "${kafka.group-id}")
    public void consume(final ConsumerRecord<String, People> people) {

        var peopleConsumed = people.value();

        log.info("DADO CONSUMIDO: {}", peopleConsumed.toString());


//        ack.acknowledge();

    }

//    // O KafkaListener ouve o tópico e processa as mensagens
//    @KafkaListener(topics = "people-topic", groupId = "people-group")
//    public void listen(ConsumerRecord<String, People> record) {
//        People people = record.value();  // O Avro deserializa automaticamente para o objeto 'People'
//        System.out.println("Mensagem recebida: " + people.toString());
//
//        // Aqui você pode fazer qualquer lógica de negócio com o objeto 'people' recebido
//    }
}
