package com.api.restfulgym.repository;

import com.api.restfulgym.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TrainerRepository extends JpaRepository<Trainer, Integer> {
    @Query("DELETE FROM Trainer WHERE id = :trainerId")
    @Modifying
    void deleteTrainerByTrainerId(Integer trainerId);
}
