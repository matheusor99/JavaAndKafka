package com.example.matheusor99.springProducer.adapter.messagebroker.kafka;

import br.com.matheusor99.springProducer.People;

public interface KafkaProducerAdapter {

    void sendMessage(String topic, String key, People people);
}
