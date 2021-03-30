package com.su.accounting.controller;

import com.su.accounting.converter.ServiceToWeb.ServiceToWebConverter;
import com.su.accounting.exception.GlobalExceptionHandler;
import com.su.accounting.exception.InvalidParameterException;
import com.su.accounting.service.UserInfoServiceImpl;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    MockMvc mockMvc;
    @Mock UserInfoServiceImpl mockService;
    @Mock ServiceToWebConverter mockConverter;
    @InjectMocks UserController userController;

    @BeforeEach
    void setup() {
        Object[] controllers;
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                          .setControllerAdvice(new GlobalExceptionHandler())
                          .build();
    }

    @AfterEach
    void tearDown() {
        reset(mockService);
        reset(mockConverter);
    }

    @Test
    void testUserController() throws Exception {
        // arrange
        val userId = 1L;
        val username = "accounting_admin";
        val password = "accounting";

        val userInfoService = com.su.accounting.entity.service.UserInfo.builder()
                                      .id(userId)
                                      .username(username)
                                      .password(password)
                                      .build();
        doReturn(userInfoService).when(mockService).getUserInfoByUserId(anyLong());
        val userInfoWeb = com.su.accounting.entity.web.UserInfo.builder()
                                  .id(userId)
                                  .username(username)
                                  .build();
        doReturn(userInfoWeb).when(mockConverter).convert(userInfoService);
        // act assert
        mockMvc.perform(get("/v1.0/users/" + userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("{\"id\":1,\"username\":\"accounting_admin\",\"password\":null}"));
        verify(mockService).getUserInfoByUserId(anyLong());
        verify(mockConverter).convert(userInfoService);
    }

    @Test
    void testUserControllerWithInvalidUserId() throws Exception {
        // arranges
        val userId = -100L;
        doThrow(new InvalidParameterException(String.format("user %s was not found",userId)))
                .when(mockService)
                .getUserInfoByUserId(anyLong());
        // act  assert
        mockMvc.perform(get("/v1.0/users/" + userId))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().string("{\"code\":\"INVALID_PARAMETER\",\"errorType\":\"Client\",\"message\":\"userId -100 is invalid\",\"statusCode\":400}"));
    }
}