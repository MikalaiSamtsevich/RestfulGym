package com.api.restfulgym.dto;

import com.api.restfulgym.config.Views;
import com.api.restfulgym.model.Role;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserDTO {
    @JsonView({Views.TrainerView.class, Views.ScheduleView.class, Views.UserView.class})
    private int id;

    @NotBlank(message = "Firstname type name is required")
    @JsonView({Views.TrainerView.class, Views.ScheduleView.class, Views.UserView.class})
    private String firstName;

    @NotBlank(message = "Lastname type name is required")
    @JsonView({Views.TrainerView.class, Views.ScheduleView.class, Views.UserView.class})
    private String lastName;

    @NotBlank(message = "Username type name is required")
    @JsonView({Views.TrainerView.class, Views.ScheduleView.class, Views.UserView.class})
    private String username;

    @NotBlank(message = "email type name is required")
    @Email(message = "Invalid email format")
    @JsonView(Views.UserView.class)
    private String email;

    @JsonView(Views.UserView.class)
    private TrainerDTO trainer;

    @JsonView(Views.UserView.class)
    private Collection<Role> roles;

    @JsonView(Views.UserView.class)
    private Collection<ScheduleDTO> schedules;

    @JsonView(Views.UserView.class)
    private Collection<NotificationsDTO> notificationsById;
}
