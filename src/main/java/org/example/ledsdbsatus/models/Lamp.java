package org.example.ledsdbsatus.models;

import jakarta.persistence.*;
import lombok.*;
import org.example.ledsdbsatus.exceptions.validations.IsRequired;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lamps_status")
public class Lamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @IsRequired
    private String label;
    private boolean status;
    private String description;

}

