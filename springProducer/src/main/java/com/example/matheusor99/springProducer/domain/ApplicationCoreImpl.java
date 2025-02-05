package com.example.matheusor99.springProducer.domain;

import br.com.matheusor99.springProducer.People;
import com.example.matheusor99.springProducer.adapter.messagebroker.kafka.KafkaProducerAdapter;
import com.example.matheusor99.springProducer.adapter.network.rest.dto.PeopleDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
public class ApplicationCoreImpl implements ApplicationCore {

    private final KafkaProducerAdapter producer;
    private final String topic;

    @Autowired
    public ApplicationCoreImpl(final KafkaProducerAdapter producer,
                               @Value("${kafka.topic-name}") final String topic) {
        this.producer = producer;
        this.topic = topic;
    }

    @Override
    public String registerPeople(final PeopleDto people) {

        final long initProcess = System.currentTimeMillis();

        try {
            var id = UUID.randomUUID().toString();

            var message = People.newBuilder()
                    .setId(id)
                    .setName(people.getNome())
                    .setCpf(people.getCpf())
                    .setBooks(people.getBooks().stream().map(p -> (CharSequence) p).collect(Collectors.toList()))
                    .build();

            producer.sendMessage(topic, id, message);

            return "Mensagem enviada";
        } catch (Exception ex) {
            return "erro ao enviar mensagem kafka";
        } finally {
            log.info("tempo de processamento: {}", System.currentTimeMillis() - initProcess);
        }

    }
}
