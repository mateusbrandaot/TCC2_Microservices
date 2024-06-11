package com.agenday.gateway.controller;

import com.agenday.gateway.dto.CreateUserDto;
import com.agenday.gateway.dto.LoginUserDto;
import com.agenday.gateway.dto.RecoveryJwtTokenDto;
import com.agenday.gateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gateway")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public ResponseEntity<String> getAuthenticationTest() {
        return new ResponseEntity<>("Autenticado com sucesso", HttpStatus.OK);
    }

}