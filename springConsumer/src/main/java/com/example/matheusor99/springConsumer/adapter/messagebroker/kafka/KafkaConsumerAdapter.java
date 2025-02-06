package com.example.matheusor99.springConsumer.adapter.messagebroker.kafka;

import br.com.matheusor99.springConsumer.People;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.support.Acknowledgment;

public interface KafkaConsumerAdapter {

    void consume(ConsumerRecord<String, People> people);
}
