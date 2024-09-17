package com.api.restfulgym.service;

import com.api.restfulgym.model.TrainingType;
import com.api.restfulgym.repository.TrainingTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class TrainingTypeService {
    private final TrainingTypeRepository trainingTypeRepository;

    public TrainingType findById(Integer id) {
        return trainingTypeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("id: " + id));
    }

    public List<TrainingType> findAll() {
        return trainingTypeRepository.findAll();
    }

    public TrainingType save(TrainingType trainingTypeDTO) {
        return trainingTypeRepository.save(trainingTypeDTO);
    }

    public void deleteById(Integer id) {
        trainingTypeRepository.deleteById(id);
    }
}
