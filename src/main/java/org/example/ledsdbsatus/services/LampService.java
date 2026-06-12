package org.example.ledsdbsatus.services;

import org.example.ledsdbsatus.models.Lamp;

import java.util.List;
import java.util.Optional;

public interface LampService {
    List<Lamp> findAll();

    Lamp findById(Long id);

    Lamp save(Lamp product);

    Optional<Lamp> update(Long id, Lamp product);

    Optional<Lamp> delete(Long id);
}
