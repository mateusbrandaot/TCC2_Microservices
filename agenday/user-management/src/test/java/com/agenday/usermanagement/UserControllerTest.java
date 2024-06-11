package com.agenday.usermanagement;

import com.agenday.usermanagement.controller.UserController;
import com.agenday.usermanagement.dto.CreateUserDto;
import com.agenday.usermanagement.model.User;
import com.agenday.usermanagement.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import  com.agenday.usermanagement.indicator.RoleName;

import static com.agenday.usermanagement.indicator.RoleName.ROLE_CUSTOMER;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void createUser() throws Exception {
        CreateUserDto createUserDto = new CreateUserDto("test@example.com", "password", ROLE_CUSTOMER, "Test User");
        mockMvc.perform(post("/user-management/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"Test User\", \"email\": \"test@example.com\", \"senha\": \"password\"}"))
                .andExpect(status().isCreated());
    }
}