package com.example.matheusor99.springProducer.adapter.messagebroker.kafka;

import br.com.matheusor99.springProducer.People;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaProducerAdapterImpl implements KafkaProducerAdapter {

    private final KafkaTemplate<String, People> producer;

    public KafkaProducerAdapterImpl(final KafkaTemplate<String, People> producer) {
        this.producer = producer;
    }

    @Override
    public void sendMessage(final String topic, final String key, final People people) {

        ProducerRecord<String, People> record = new ProducerRecord<>(topic, key, people);

        producer.send(record);
        
        producer.flush();

    }
}
