package com.api.restfulgym.rest;

import com.api.restfulgym.config.Views;
import com.api.restfulgym.dto.NotificationsDTO;
import com.api.restfulgym.dto.TrainerDTO;
import com.api.restfulgym.dto.UserDTO;
import com.api.restfulgym.model.Trainer;
import com.api.restfulgym.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/clients")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping("/trainer")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @JsonView(Views.TrainerView.class)
    public ResponseEntity<Object> findUserTrainer(Principal principal) {
        Trainer trainer = userService.findByUsername(principal.getName()).getTrainer();
        if (trainer == null) {
            return ResponseEntity.ok().body(null);
        }
        TrainerDTO trainerDTO = modelMapper.map(trainer, TrainerDTO.class);
        return ResponseEntity.ok(trainerDTO);
    }

    @PutMapping("{id}/trainers")
    @JsonView(Views.UserView.class)
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<UserDTO> saveTrainerToUser(@PathVariable Integer id, Principal principal) {
        UserDTO updatedUser = modelMapper.map(userService.setTrainerByTrainerId(principal.getName(), id), UserDTO.class);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/info")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @JsonView(Views.UserView.class)
    public ResponseEntity<UserDTO> userData(Principal principal) {
        UserDTO userDTO = modelMapper.map(userService.findByUsername(principal.getName()), UserDTO.class);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/notifications")
    public ResponseEntity<Collection<NotificationsDTO>> userNotifications(Principal principal) {
        UserDTO userDTO = modelMapper.map(userService.findByUsername(principal.getName()), UserDTO.class);
        return ResponseEntity.ok(userDTO.getNotificationsById());
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Integer id) {
        userService.deleteById(id);
    }
}
