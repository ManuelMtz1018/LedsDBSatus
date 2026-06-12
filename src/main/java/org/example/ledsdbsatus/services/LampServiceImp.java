package org.example.ledsdbsatus.services;

import jakarta.transaction.Transactional;
import org.example.ledsdbsatus.models.Lamp;
import org.example.ledsdbsatus.repository.LampRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LampServiceImp implements LampService {

    private LampRepository lampRepository;

    public LampServiceImp(LampRepository repository) {
        this.lampRepository = repository;
    }

    @Transactional
    @Override
    public List<Lamp> findAll() {
        return (List<Lamp>) lampRepository.findAll();
    }

    @Transactional
    @Override
    public Lamp findById(Long id) {
        return lampRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lamp not found"));
    }

    @Transactional
    @Override
    public Lamp save(Lamp lamp) {
        return lampRepository.save(lamp);
    }

    @Override
    public Optional<Lamp> update(Long id, Lamp lamp) {
        Optional<Lamp> existingLamp = lampRepository.findById(id);
        if (existingLamp.isPresent()) {
            Lamp updatedLamp = existingLamp.get();
            updatedLamp.setLabel(lamp.getLabel());
            updatedLamp.setStatus(lamp.isStatus());
            updatedLamp.setDescription(lamp.getDescription());
            return Optional.of(lampRepository.save(updatedLamp));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Lamp> delete(Long id) {
        Optional<Lamp> existingLamp = lampRepository.findById(id);
        existingLamp.ifPresent(lamp->{
            lampRepository.delete(lamp);
        });
        return existingLamp;
    }
}
