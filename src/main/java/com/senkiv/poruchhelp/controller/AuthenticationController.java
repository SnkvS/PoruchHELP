package com.senkiv.poruchhelp.controller;

import com.senkiv.poruchhelp.dto.UserLoginRequestDto;
import com.senkiv.poruchhelp.dto.UserLoginResponseDto;
import com.senkiv.poruchhelp.dto.UserRegistrationRequestDto;
import com.senkiv.poruchhelp.dto.UserResponseDto;
import com.senkiv.poruchhelp.exception.RegistrationException;
import com.senkiv.poruchhelp.service.AuthenticationService;
import com.senkiv.poruchhelp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication API")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/registration")
    @Operation(description = "Register new user.")
    public UserResponseDto register(
            @RequestBody @Valid UserRegistrationRequestDto dto) throws RegistrationException {
        return userService.register(dto);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Login for registered users.")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }
}
