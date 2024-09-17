package com.api.restfulgym.dto;

import com.api.restfulgym.config.Views;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TrainingTypeDTO {
    @JsonView({Views.TrainerView.class, Views.ScheduleView.class,
            Views.UserView.class, Views.TrainingTypeView.class})
    private int id;
    @JsonView({Views.TrainerView.class, Views.ScheduleView.class,
            Views.UserView.class, Views.TrainingTypeView.class})
    @NotBlank(message = "Training type name is required")
    private String name;
}
