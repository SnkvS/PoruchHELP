package com.senkiv.poruchhelp.service.impl;

import com.senkiv.poruchhelp.dto.UserRegistrationRequestDto;
import com.senkiv.poruchhelp.dto.UserResponseDto;
import com.senkiv.poruchhelp.exception.RegistrationException;
import com.senkiv.poruchhelp.mapper.UserMapper;
import com.senkiv.poruchhelp.model.Role.RoleName;
import com.senkiv.poruchhelp.model.User;
import com.senkiv.poruchhelp.repository.RoleRepository;
import com.senkiv.poruchhelp.repository.UserRepository;
import com.senkiv.poruchhelp.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final String USER_WITH_SUCH_EMAIL_ALREADY_EXISTS =
            "User with such email already exists -> %s";
    private static final String CANNOT_ASSIGN_DEFAULT_ROLE_TO_USER =
            "Cannot assign default role to user.";
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final UserMapper mapper;

    @Transactional
    @Override
    public UserResponseDto register(UserRegistrationRequestDto dto) throws RegistrationException {
        if (userRepository.existsByEmail(dto.email())) {
            throw new RegistrationException(
                    USER_WITH_SUCH_EMAIL_ALREADY_EXISTS.formatted(dto.email()));
        }
        User newUser = mapper.toModel(dto);
        newUser.setPassword(encoder.encode(newUser.getPassword()));
        newUser.setRoles(Set.of(roleRepository.findByRoleName(RoleName.USER).orElseThrow(
                () -> new EntityNotFoundException(CANNOT_ASSIGN_DEFAULT_ROLE_TO_USER))));
        userRepository.save(newUser);
        return mapper.toDto(newUser);
    }
}
