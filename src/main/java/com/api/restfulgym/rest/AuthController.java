package com.api.restfulgym.rest;

import com.api.restfulgym.config.Views;
import com.api.restfulgym.dto.JwtRequest;
import com.api.restfulgym.dto.JwtResponse;
import com.api.restfulgym.dto.RegistrationUserDto;
import com.api.restfulgym.dto.UserDTO;
import com.api.restfulgym.service.AuthService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/auth", produces = "application/json")
public class AuthController {
    private final AuthService authService;
    ModelMapper modelMapper;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody JwtRequest authRequest) {

        String token = authService.createAuthToken(authRequest.getUsername(), authRequest.getPassword());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/register")
    @JsonView(Views.UserView.class)
    public ResponseEntity<UserDTO> register(@RequestBody RegistrationUserDto userDto) {
        UserDTO userDTO = modelMapper.map(authService.create(userDto), UserDTO.class);
        return ResponseEntity.ok(userDTO);
    }
}