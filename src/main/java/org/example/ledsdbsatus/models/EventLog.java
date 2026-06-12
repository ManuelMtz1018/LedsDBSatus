package org.example.ledsdbsatus.models;

import java.time.LocalDateTime;

public record EventLog(String eventType, LocalDateTime timestamp) {}
