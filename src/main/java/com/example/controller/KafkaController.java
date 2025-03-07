package com.example.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.common.KafkaFuture;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller("/api/kafka")
public class KafkaController {

    private final AdminClient adminClient;

    @Inject
    public KafkaController(AdminClient adminClient) {
        this.adminClient = adminClient;
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