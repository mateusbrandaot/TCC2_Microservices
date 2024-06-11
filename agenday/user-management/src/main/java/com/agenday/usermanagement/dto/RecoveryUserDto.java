package com.agenday.usermanagement.dto;

import com.agenday.usermanagement.model.Role;

import java.util.List;

public record RecoveryUserDto(

        Long id,
        String email,
        String Token,
        Long instituitionId
) {
}
