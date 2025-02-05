package com.example.matheusor99.springProducer.adapter.messagebroker.kafka;

import br.com.matheusor99.springProducer.People;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaProducerAdapterImpl implements KafkaProducerAdapter {

    private final KafkaTemplate<String, People> producer;

    public KafkaProducerAdapterImpl(final KafkaTemplate<String, People> producer) {
        this.producer = producer;
    }

    @Override
    public void sendMessage(final String topic, final String key, final People people) {

        final ProducerRecord<String, People> record = new ProducerRecord<>(topic, key, people);

        producer.send(record);

        log.info("Mensagem enviada!");

        producer.flush();

    }
}
