package com.api.restfulgym.service;

import com.api.restfulgym.dto.RegistrationUserDto;
import com.api.restfulgym.model.User;
import com.api.restfulgym.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final TrainerService trainerService;
    private final RoleService roleService;

    private PasswordEncoder passwordEncoder;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException(username));
    }

    public User findById(Integer id) {
        return userRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("id: " + id));
    }

    public User setTrainerByTrainerId(String username, Integer trainerId) {
        User user = findByUsername(username);
        user.setTrainer(trainerService.findById(trainerId));
        var userSchedule = user.getSchedules();
        userSchedule.forEach(schedule -> {
            schedule.setTrainer(user.getTrainer());
        });
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role ->
                                new SimpleGrantedAuthority(role.getName()))
                        .toList()
        );
    }

    public User createNewUser(RegistrationUserDto userDto) {

        User user = User.builder()
                .username(userDto.getUsername())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();
        user.setRoles(List.of(roleService.findByName("ROLE_USER")));
        return userRepository.save(user);
    }

    public boolean existsUserByUsername(String username) {
        return userRepository.existsUserByUsername(username);
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}
