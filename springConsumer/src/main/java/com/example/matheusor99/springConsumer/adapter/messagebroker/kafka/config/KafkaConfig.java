package com.example.matheusor99.springConsumer.adapter.messagebroker.kafka.config;

import br.com.matheusor99.springProducer.People;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import lombok.Data;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.Properties;

@Configuration
@ConfigurationProperties(prefix = "kafka")
@Data
@Validated
@Order(1)
public class KafkaConfig {

    private String bootstrapServers;
    private String schemaRegistryUrl;
    private String groupId;
    private String autoOffsetReset;
    private boolean specificAvroReader;
    private String keySerializer;
    private String valueSerializer;

    @Bean
    ConsumerFactory<String, Object> consumerConfig() {
        final var configProps = new HashMap<String, Object>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
        configProps.put("schema.registry.url", schemaRegistryUrl);
        configProps.put("specific.avro.reader", true);
        return new DefaultKafkaConsumerFactory<>(configProps);
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, People> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, People> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerConfig());
//        factory.setConcurrency(5); // Pode processar até 5 mensagens simultaneamente
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);

        return factory;
    }
}
