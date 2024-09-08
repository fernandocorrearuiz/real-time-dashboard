package com.example.dashboard;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TrafficDataConsumer {

    @KafkaListener(topics = "traffic-data", groupId = "traffic-dashboard-group")
    public void listenTrafficData(String message) {
        System.out.println("Received Message in group 'traffic-dashboard-group': " + message);
        // Process the message further
    }
}
