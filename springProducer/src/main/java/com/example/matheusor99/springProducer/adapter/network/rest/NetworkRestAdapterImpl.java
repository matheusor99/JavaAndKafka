package com.example.matheusor99.springProducer.adapter.network.rest;

import com.example.matheusor99.springProducer.adapter.network.rest.dto.PeopleDto;
import com.example.matheusor99.springProducer.domain.ApplicationCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NetworkRestAdapterImpl implements NetworkRestAdapter {
    private final ApplicationCore domain;

    @Autowired
    public NetworkRestAdapterImpl(final ApplicationCore domain) {
        this.domain = domain;
    }

    @Override
    public ResponseEntity<String> registerPeople(final PeopleDto people) {
        final String result = domain.registerPeople(people);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
