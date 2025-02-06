package com.ivanzvieriev.kafkamanager.config;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KafkaConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConfig.class.getSimpleName());

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String host;

    @Bean
    public AdminClient getKafkaAdmin() {
        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, host);
        return KafkaAdminClient.create(properties);
    }

}
