package com.api.restfulgym.rest;

import com.api.restfulgym.config.Views;
import com.api.restfulgym.dto.TrainingTypeDTO;
import com.api.restfulgym.model.TrainingType;
import com.api.restfulgym.service.TrainingTypeService;
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
@RequestMapping("/api/v1/training_types")
public class TrainingTypeController {
    TrainingTypeService trainingTypeService;
    ModelMapper modelMapper;

    @GetMapping
    @JsonView(Views.TrainingTypeView.class)
    public ResponseEntity<List<TrainingTypeDTO>> findAll() {
        Type listType = new TypeToken<List<TrainingTypeDTO>>() {
        }.getType();
        return ResponseEntity.ok(modelMapper.map(trainingTypeService.findAll(), listType));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @JsonView(Views.TrainingTypeView.class)
    public ResponseEntity<TrainingTypeDTO> save(@Valid @RequestBody TrainingTypeDTO trainingTypeDTO) {
        TrainingType savedTrainingType = trainingTypeService.save(modelMapper.map(trainingTypeDTO, TrainingType.class));
        TrainingTypeDTO savedTrainingTypeDTO = modelMapper.map(savedTrainingType, TrainingTypeDTO.class);
        return ResponseEntity.ok(savedTrainingTypeDTO);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Integer id) {
        trainingTypeService.deleteById(id);
    }
}