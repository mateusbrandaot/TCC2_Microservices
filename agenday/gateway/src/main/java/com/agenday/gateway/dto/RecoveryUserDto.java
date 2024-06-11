package com.agenday.gateway.dto;

import com.agenday.gateway.model.Role;

import java.util.List;

public record RecoveryUserDto(

        Long id,
        String email,
        List<Role> roles

) {
}
