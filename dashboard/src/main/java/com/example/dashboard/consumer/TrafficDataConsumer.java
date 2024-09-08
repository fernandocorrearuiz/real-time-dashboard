package com.example.dashboard.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class TrafficDataConsumer {

    private final SimpMessagingTemplate template;
    private final ObjectMapper objectMapper;

    @Autowired
    public TrafficDataConsumer(SimpMessagingTemplate template, ObjectMapper objectMapper) {
        this.template = template;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "traffic-data", groupId = "traffic-dashboard-group")
    public void listenTrafficData(String message) {
        System.out.println("Received Message in group 'traffic-dashboard-group': " + message);
        try {
            // Send the message to WebSocket subscribers
            String jsonMessage = objectMapper.writeValueAsString(new MessageWrapper(message));
            System.out.println("jsonMessage': " + jsonMessage);
            template.convertAndSend("/topic/traffic", jsonMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class MessageWrapper {
        private String message;

        public MessageWrapper(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
