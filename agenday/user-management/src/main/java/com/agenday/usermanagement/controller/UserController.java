package com.agenday.usermanagement.controller;

import com.agenday.usermanagement.dto.*;
import com.agenday.usermanagement.model.User;
import com.agenday.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-management")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<RecoveryUserDto> authenticateUser(@RequestBody LoginUserDto loginUserDto) {
        RecoveryUserDto recoveryUserDto = userService.authenticateUser(loginUserDto);
        return new ResponseEntity<>(recoveryUserDto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody CreateUserDto createUserDto) throws Exception{
        UserResponseDTO userResponseDTO= userService.createUser(createUserDto);
        return new ResponseEntity<>(userResponseDTO,HttpStatus.CREATED);
    }

}