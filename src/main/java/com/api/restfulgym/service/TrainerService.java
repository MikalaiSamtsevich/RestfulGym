package com.api.restfulgym.service;

import com.api.restfulgym.model.Trainer;
import com.api.restfulgym.repository.TrainerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TrainerService {
    private final TrainerRepository trainerRepository;

    public List<Trainer> findAll() {
        return trainerRepository.findAll();
    }

    public Trainer save(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    public Trainer findById(Integer id) {
        return trainerRepository.findById(id).orElseThrow(() -> new RuntimeException("not found. id: " + id));
    }

    @Transactional
    public void deleteById(Integer id) {
        trainerRepository.deleteTrainerByTrainerId(id);
    }
}
