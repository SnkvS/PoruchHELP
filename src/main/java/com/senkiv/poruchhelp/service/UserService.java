package com.senkiv.poruchhelp.service;

import com.senkiv.poruchhelp.dto.UserRegistrationRequestDto;
import com.senkiv.poruchhelp.dto.UserResponseDto;
import com.senkiv.poruchhelp.exception.RegistrationException;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto dto) throws RegistrationException;
}
