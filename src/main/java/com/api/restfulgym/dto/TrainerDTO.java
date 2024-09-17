package com.api.restfulgym.dto;

import com.api.restfulgym.config.Views;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TrainerDTO {
    @JsonView({Views.TrainerView.class, Views.ScheduleView.class, Views.UserView.class})
    Integer id;
    @NotBlank(message = "First name is required")
    @JsonView({Views.TrainerView.class, Views.UserView.class, Views.ScheduleView.class})
    private String firstName;

    @NotBlank(message = "Last name is required")
    @JsonView({Views.TrainerView.class, Views.UserView.class, Views.ScheduleView.class})
    private String lastName;

    @NotBlank(message = "Phone number is required")
    @JsonView(Views.TrainerView.class)
    private String phoneNumber;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @JsonView({Views.TrainerView.class, Views.ScheduleView.class, Views.UserView.class})
    private String email;

    @NotBlank(message = "Photo is required")
    @JsonView(Views.TrainerView.class)
    private String photo;

    @DecimalMin(value = "0.0", message = "Cost per session must be non-negative")
    @JsonView(Views.TrainerView.class)
    private BigDecimal costPerSession;

    @JsonView(Views.TrainerView.class)
    private List<ScheduleDTO> schedules;
}