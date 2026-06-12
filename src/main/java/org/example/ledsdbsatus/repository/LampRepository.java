package org.example.ledsdbsatus.repository;

import org.example.ledsdbsatus.models.Lamp;
import org.springframework.data.repository.CrudRepository;

public interface LampRepository extends CrudRepository<Lamp, Long> {

}
