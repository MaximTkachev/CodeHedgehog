package com.qwerty.codehedgehog.service;

import com.qwerty.codehedgehog.converter.RoleConverter;
import com.qwerty.codehedgehog.dto.Role.CreateRoleDto;
import com.qwerty.codehedgehog.dto.Role.RoleDto;
import com.qwerty.codehedgehog.dto.Role.UpdateRoleDto;
import com.qwerty.codehedgehog.entity.RoleEntity;
import com.qwerty.codehedgehog.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    @Transactional
    public RoleDto createRole(CreateRoleDto createRoleDto) {
        var role = RoleConverter.ConvertCreateDtoToEntity(createRoleDto);
        var savedEntity = roleRepository.save(role);
        return RoleConverter.ConvertEntityToDto(savedEntity);
    }

    @Transactional(readOnly = true)
    public List<RoleDto> getListOfRoles() {
        var roles = roleRepository.findAll();
        List<RoleDto> result = new ArrayList<>();
        for(RoleEntity role : roles) {
            var tempRole = RoleConverter.ConvertEntityToDto(role);
            result.add(tempRole);
        }
        return result;
    }

    @Transactional(readOnly = true)
    public RoleDto getRoleById(String id) {
        var role = roleRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        return RoleConverter.ConvertEntityToDto(role);
    }

    @Transactional
    public RoleDto editRole(String id, UpdateRoleDto updateRoleDto) {
        var role = roleRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        role.setName(updateRoleDto.getName());
        return RoleConverter.ConvertEntityToDto(role);
    }

    @Transactional
    public void deleteRole(String id) {
        roleRepository.deleteById(id);
    }
}
