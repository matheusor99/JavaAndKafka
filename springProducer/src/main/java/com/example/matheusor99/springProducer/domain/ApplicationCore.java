package com.example.matheusor99.springProducer.domain;

import com.example.matheusor99.springProducer.adapter.network.rest.dto.PeopleDto;

public interface ApplicationCore {
    String registerPeople(PeopleDto people);
}
