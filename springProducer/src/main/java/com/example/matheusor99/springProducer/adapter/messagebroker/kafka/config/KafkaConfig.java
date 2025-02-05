package com.example.matheusor99.springProducer.adapter.messagebroker.kafka.config;

import br.com.matheusor99.springProducer.People;
import lombok.Data;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "kafka")
@Data
@Validated
@Order(1)
public class KafkaConfig {
    private String bootstrapServers;
    private String topicName;
    private String schemaRegistryUrl;
    private String keySerializer;
    private String valueSerializer;

    //criar um topico
    @Bean
    NewTopic createTopic() {
        return new NewTopic(topicName, 1, (short) 1);
    }
    
    @Bean
    ProducerFactory<String, People> configProducer() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        configProps.put("schema.registry.url", schemaRegistryUrl);

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, People> kafkaTemplate() {
        return new KafkaTemplate<>(configProducer());
    }
}
