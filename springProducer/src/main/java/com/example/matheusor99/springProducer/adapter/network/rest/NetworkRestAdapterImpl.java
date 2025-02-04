package com.example.matheusor99.springProducer.adapter.network.rest;

import com.example.matheusor99.springProducer.adapter.network.rest.dto.PeopleDto;
import com.example.matheusor99.springProducer.domain.ApplicationCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class NetworkRestAdapterImpl implements NetworkRestAdapter {
    private final ApplicationCore domain;

    @Autowired
    public NetworkRestAdapterImpl(final ApplicationCore domain) {
        this.domain = domain;
    }

    @Override
    public ResponseEntity<String> registerPeople(final PeopleDto people) {
        return null;
    }
}
