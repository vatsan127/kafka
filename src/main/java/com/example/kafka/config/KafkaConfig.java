package com.example.kafka.config;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "kafka.properties")
public class KafkaConfig {
    private static final Logger log = LoggerFactory.getLogger(KafkaConfig.class);
    private String bootstrapServers;
    private String schemaRegistryUrl;
    private String avroTopic;

    @PostConstruct
    public void init() {
        log.info("Initializing KafkaConfig : {} ", this);
    }
}
