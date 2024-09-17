package com.api.restfulgym.rest;

import com.api.restfulgym.config.Views;
import com.api.restfulgym.dto.TrainerDTO;
import com.api.restfulgym.model.Trainer;
import com.api.restfulgym.service.TrainerService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/trainers")
public class TrainerController {
    private final TrainerService trainerService;
    ModelMapper modelMapper;

    @GetMapping()
    @JsonView(Views.TrainerView.class)
    public ResponseEntity<List<TrainerDTO>> findAll() {
        List<Trainer> trainers = trainerService.findAll();
        Type listType = new TypeToken<List<TrainerDTO>>() {
        }.getType();
        return ResponseEntity.ok(modelMapper.map(trainers, listType));
    }

    @PostMapping(consumes = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    @JsonView(Views.TrainerView.class)
    public ResponseEntity<TrainerDTO> save(@Valid @RequestBody TrainerDTO trainerDTO) {
        Trainer trainer = modelMapper.map(trainerDTO, Trainer.class);
        Trainer savedTrainer = trainerService.save(trainer);
        return ResponseEntity.ok(modelMapper.map(savedTrainer, TrainerDTO.class));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Integer id) {
        trainerService.deleteById(id);
    }
}
