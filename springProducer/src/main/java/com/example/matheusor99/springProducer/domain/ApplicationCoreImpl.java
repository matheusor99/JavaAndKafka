package com.example.matheusor99.springProducer.domain;

import com.example.matheusor99.springProducer.adapter.messagebroker.kafka.KafkaProducerAdapter;
import com.example.matheusor99.springProducer.adapter.network.rest.dto.PeopleDto;
import org.springframework.beans.factory.annotation.Autowired;

public class ApplicationCoreImpl implements ApplicationCore {

    private final KafkaProducerAdapter producer;

    @Autowired
    public ApplicationCoreImpl(final KafkaProducerAdapter producer) {
        this.producer = producer;
    }

    @Override
    public String registerPeople(final PeopleDto people) {
        return "";
    }
}
