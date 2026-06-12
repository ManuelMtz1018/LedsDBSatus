package org.example.ledsdbsatus.models;

import jakarta.persistence.*;
import org.example.ledsdbsatus.exceptions.validations.IsRequired;

@Entity
@Table(name = "lamps_status")
public class Lamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @IsRequired
    private String label;
    private boolean status;
    private String description;

    public Lamp() {}

    public Lamp(Long id, String label, boolean status, String description) {
        this.id = id;
        this.label = label;
        this.status = status;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

