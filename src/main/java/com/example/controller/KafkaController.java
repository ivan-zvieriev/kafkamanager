package com.example.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.KafkaFuture;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

@Controller("/api/kafka")
public class KafkaController {

    private final AdminClient adminClient;

    public KafkaController() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("client.id", "kafka-manager");
        this.adminClient = AdminClient.create(props);
    }

    @Get("/topics")
    public List<String> listTopics() throws Exception {
        ListTopicsOptions options = new ListTopicsOptions();
        options.timeoutMs(5000);
        ListTopicsResult result = adminClient.listTopics(options);
        KafkaFuture<Collection<String>> names = result.names();
        return new ArrayList<>(names.get());
    }
} 