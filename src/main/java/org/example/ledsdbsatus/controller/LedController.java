package org.example.ledsdbsatus.controller;

import jakarta.validation.Valid;
import org.example.ledsdbsatus.models.Lamp;
import org.example.ledsdbsatus.services.EventLogService;
import org.example.ledsdbsatus.services.LampService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.example.ledsdbsatus.models.constants.Constants.*;

@RestController
@RequestMapping("/leds")
public class LedController {

    private static final Logger log = LoggerFactory.getLogger(LedController.class);
    private LampService lampService;
    private EventLogService eventLogService;

    public LedController(LampService lampService, EventLogService eventLogService) {
        this.lampService = lampService;
        this.eventLogService = eventLogService;
    }

    @GetMapping("/all")
    public ResponseEntity viewLamps() {
        List<Lamp> lamps =  lampService.findAll();
        int size=lamps.size();
        log.info(GET_ALL_LOG+"{}", size);
        eventLogService.eventRegister(GET_ALL_LOG + size);
        return ResponseEntity.ok(lamps);
    }

    @GetMapping("/status/{id}")
    public ResponseEntity viewLamp(@PathVariable Long id) {
        Lamp lamp= lampService.findById(id);
        if(Optional.of(lamp).isPresent()) {
            log.info(GET_LOG+"{}: {}", id, lamp);
            eventLogService.eventRegister(GET_LOG + id);
            return ResponseEntity.ok(lamp);
        };
        log.warn(GET_ERROR+"{}", id);
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity addLamp(@Valid @RequestBody Lamp lamp) {
        Lamp savedLamp = lampService.save(lamp);
        if (savedLamp != null) {
            log.info(POST_LOG+"{}", savedLamp.getId());
            eventLogService.eventRegister(POST_LOG + savedLamp.getId());
            return ResponseEntity.ok(savedLamp);
        }
        log.warn(POST_ERROR+"{}", lamp);
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateLamp(@PathVariable Long id,@Valid @RequestBody Lamp lamp) {
        return lampService.update(id, lamp)
                .map(updated -> {
                    log.info(PUT_LOG+"{}", updated.getId());
                    eventLogService.eventRegister(PUT_LOG + updated.getId());
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> {
                    log.warn(PUT_ERROR+"{}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteLamp(@PathVariable Long id) {
        return lampService.delete(id)
                .map(deleted -> {
                    log.info(DELETE_LOG+"{}", deleted.getId());
                    eventLogService.eventRegister(DELETE_LOG + deleted.getId());
                    return ResponseEntity.ok(deleted);
                })
                .orElseGet(() -> {
                    log.warn(DELETE_ERROR+"{}", id);
                    return ResponseEntity.notFound().build();
                });
    }

}
