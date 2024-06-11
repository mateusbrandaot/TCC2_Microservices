package com.agenday.gateway.dto;

import com.agenday.gateway.indicator.RoleName;

public record CreateUserDto(

        String email,
        String password,
        RoleName role

) {
}