package com.senkiv.poruchhelp.mapper;

import com.senkiv.poruchhelp.config.MapperConfig;
import com.senkiv.poruchhelp.dto.UserRegistrationRequestDto;
import com.senkiv.poruchhelp.dto.UserResponseDto;
import com.senkiv.poruchhelp.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toDto(User user);

    User toModel(UserRegistrationRequestDto userRegistrationRequestDto);
}
