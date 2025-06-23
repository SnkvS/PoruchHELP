package com.senkiv.poruchhelp.service;

import com.senkiv.poruchhelp.model.Role;
import com.senkiv.poruchhelp.model.Role.RoleName;
import com.senkiv.poruchhelp.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    @PostConstruct
    public void init() {
        for (RoleName role : Role.RoleName.values()) {
            if (!roleRepository.existsByRoleName(role)) {
                Role entity = new Role();
                entity.setRoleName(role);
                roleRepository.save(entity);
            }
        }
    }
}
