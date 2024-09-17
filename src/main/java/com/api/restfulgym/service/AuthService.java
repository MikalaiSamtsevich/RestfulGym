package com.api.restfulgym.service;

import com.api.restfulgym.dto.RegistrationUserDto;
import com.api.restfulgym.exception.AuthError;
import com.api.restfulgym.model.User;
import com.api.restfulgym.util.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    public String createAuthToken(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,
                password));
        UserDetails userDetails = userService.loadUserByUsername(username);
        return jwtUtils.generateToken(userDetails);
    }

    public User create(@RequestBody RegistrationUserDto userDto) {
        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            throw new AuthError(HttpStatus.BAD_REQUEST.value(),
                    "passwords don't match");
        }
        if (userService.existsUserByUsername(userDto.getUsername())) {
            throw new AuthError(HttpStatus.BAD_REQUEST.value(),
                    "user already exists");
        }
        return userService.createNewUser(userDto);
    }

}
