package com.api.restfulgym.service;

import com.api.restfulgym.model.Role;
import com.api.restfulgym.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role findByName(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new RuntimeException(name));
    }
}
