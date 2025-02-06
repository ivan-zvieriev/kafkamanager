package com.ivanzvieriev.kafkamanager.controller;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@RestController
public class HelloWorldController {

    @Autowired
    private AdminClient adminClient;

    @GetMapping("/")
    public String greetings() {
        List<NewTopic> topicsList = new ArrayList<>();
        NewTopic topic1 = new NewTopic("topic_1", 1, (short)1);
        NewTopic topic2 = new NewTopic("topic_2", 1, (short)1);
        topicsList.add(topic1);
        topicsList.add(topic2);
        CreateTopicsResult ctr = adminClient.createTopics(topicsList);
        ListTopicsResult result = adminClient.listTopics();
        String stringResult = "";
        try {
            for (String topicName : result.names().get()) stringResult += topicName;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return stringResult;
    }
}
