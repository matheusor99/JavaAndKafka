package com.example.matheusor99.springProducer.adapter.network.rest;

import com.example.matheusor99.springProducer.adapter.network.rest.dto.PeopleDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("people")
public interface NetworkRestAdapter {

    @PostMapping
    ResponseEntity<String> registerPeople(@RequestBody PeopleDto people);
}
