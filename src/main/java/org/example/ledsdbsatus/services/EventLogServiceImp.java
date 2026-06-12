package org.example.ledsdbsatus.services;

import org.example.ledsdbsatus.models.EventLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;

@Service
public class EventLogServiceImp implements EventLogService {
    private static final Logger log = LoggerFactory.getLogger(EventLogServiceImp.class);
    private final RestClient restClient;

    @Value("${iot.events.base-url}")
    private String baseUrlDB;

    @Value("${iot.events.add-endpoint}")
    private String postEndpoint;

    public EventLogServiceImp(RestClient.Builder builder) {
        this.restClient = builder.baseUrl(baseUrlDB).build();
    }

    @Async
    public void eventRegister(String type) {
        try {
            restClient.post()
                    .uri(postEndpoint)
                    .body(new EventLog(type, LocalDateTime.now()))
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception e) {
            log.warn("The event couldn't be registered: {}", e.getMessage());
        }
    }
}
