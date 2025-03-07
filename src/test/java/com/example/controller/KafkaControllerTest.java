package com.example.controller;

import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.common.KafkaFuture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@MicronautTest
@ExtendWith(MockitoExtension.class)
class KafkaControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Mock
    private AdminClient adminClient;

    @Mock
    private ListTopicsResult listTopicsResult;

    @Mock
    private KafkaFuture<Collection<String>> kafkaFuture;

    private KafkaController controller;

    @BeforeEach
    void setUp() {
        controller = new KafkaController();
        // Use reflection to set the mocked adminClient
        try {
            java.lang.reflect.Field adminClientField = KafkaController.class.getDeclaredField("adminClient");
            adminClientField.setAccessible(true);
            adminClientField.set(controller, adminClient);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set up test", e);
        }
    }

    @Test
    void testListTopics() throws Exception {
        // Prepare test data
        List<String> expectedTopics = Arrays.asList("topic1", "topic2", "topic3");

        // Set up mocks
        when(adminClient.listTopics(any())).thenReturn(listTopicsResult);
        when(listTopicsResult.names()).thenReturn(kafkaFuture);
        when(kafkaFuture.get()).thenReturn(expectedTopics);

        // Execute test
        List<String> actualTopics = controller.listTopics();

        // Verify results
        assertEquals(expectedTopics, actualTopics);
    }

    @Test
    void testListTopicsEmpty() throws Exception {
        // Prepare test data
        List<String> expectedTopics = List.of();

        // Set up mocks
        when(adminClient.listTopics(any())).thenReturn(listTopicsResult);
        when(listTopicsResult.names()).thenReturn(kafkaFuture);
        when(kafkaFuture.get()).thenReturn(expectedTopics);

        // Execute test
        List<String> actualTopics = controller.listTopics();

        // Verify results
        assertEquals(expectedTopics, actualTopics);
    }
} 