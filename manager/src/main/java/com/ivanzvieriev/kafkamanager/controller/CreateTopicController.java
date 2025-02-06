package com.ivanzvieriev.kafkamanager.controller;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CreateTopicController {

    @Autowired
    private AdminClient adminClient;

    @GetMapping("/create")
    public String createTopic() {
        List<NewTopic> list = new ArrayList<>();
        list.add(new NewTopic("topic_3", 1, (short)1));
        adminClient.createTopics(list);
        return "Created topic";
    }
}
