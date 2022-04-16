package com.qwerty.codehedgehog.controller;

import com.qwerty.codehedgehog.dto.Role.CreateRoleDto;
import com.qwerty.codehedgehog.dto.Role.RoleDto;
import com.qwerty.codehedgehog.dto.Role.UpdateRoleDto;
import com.qwerty.codehedgehog.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
@Tag(name = "Role")
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    public RoleDto createRole(@Validated @RequestBody CreateRoleDto createRoleDto) {
        return roleService.createRole(createRoleDto);
    }

    @GetMapping
    public List<RoleDto> get_list_of_roles() {
        return roleService.getListOfRoles();
    }

    @GetMapping("/{role_id}")
    public RoleDto get_role(@PathVariable UUID role_id){
        return roleService.getRoleById(role_id.toString());
    }

    @PatchMapping("/{role_id}")
    public RoleDto edit_role(@PathVariable UUID role_id, @Validated @RequestBody UpdateRoleDto updateRoleDto){
        return roleService.editRole(role_id.toString(), updateRoleDto);
    }

    @DeleteMapping("/{role_id}")
    public void delete_role(@PathVariable UUID role_id) {
        roleService.deleteRole(role_id.toString());
    }
}
