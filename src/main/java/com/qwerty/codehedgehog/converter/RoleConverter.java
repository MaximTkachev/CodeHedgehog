package com.qwerty.codehedgehog.converter;

import com.qwerty.codehedgehog.dto.Role.CreateRoleDto;
import com.qwerty.codehedgehog.dto.Role.RoleDto;
import com.qwerty.codehedgehog.entity.RoleEntity;

import java.util.UUID;

public class RoleConverter {
    public static RoleEntity ConvertCreateDtoToEntity(CreateRoleDto roleDto) {
        return new RoleEntity(
                UUID.randomUUID().toString(),
                roleDto.getName()
        );
    }

    public static RoleDto ConvertEntityToDto(RoleEntity roleEntity) {
        return new RoleDto(
                roleEntity.getId(),
                roleEntity.getName()
        );
    }
}
