package com.api.restfulgym.dto;

import com.api.restfulgym.config.Views;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ScheduleDTO {
    @JsonView({Views.TrainerView.class, Views.ScheduleView.class, Views.UserView.class})
    private int id;

    @NotBlank(message = "Start time is required")
    @JsonView({Views.TrainerView.class, Views.ScheduleView.class, Views.UserView.class})
    private Instant startTime;

    @NotBlank(message = "Start time is required")
    @JsonView({Views.TrainerView.class, Views.ScheduleView.class, Views.UserView.class})
    private Instant endTime;

    @JsonView(Views.ScheduleView.class)
    private TrainerDTO trainer;

    @JsonView(Views.ScheduleView.class)
    private UserDTO user;

    @JsonView({Views.TrainerView.class, Views.ScheduleView.class, Views.UserView.class})
    private TrainingTypeDTO trainingType;

}