package com.example.matheusor99.springProducer.adapter.network.rest.dto;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Jacksonized
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class PeopleDto {
    private String nome;
    private String cpf;
    private List<String> books;
}
