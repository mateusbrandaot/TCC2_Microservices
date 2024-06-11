package com.agenday.usermanagement.dto;

import com.agenday.usermanagement.indicator.RoleName;

public record CreateUserDto(

        String email,
        String password,
        RoleName role,
        String name

) {
}