package com.senkiv.poruchhelp.service;

import com.senkiv.poruchhelp.dto.UserLoginRequestDto;
import com.senkiv.poruchhelp.dto.UserLoginResponseDto;

public interface AuthenticationService {
    UserLoginResponseDto authenticate(UserLoginRequestDto requestDto);
}
