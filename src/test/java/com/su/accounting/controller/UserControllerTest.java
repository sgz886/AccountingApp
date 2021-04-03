package com.su.accounting.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.su.accounting.converter.service2web.ServiceToWebConverter;
import com.su.accounting.exception.GlobalExceptionHandler;
import com.su.accounting.exception.InvalidParameterException;
import com.su.accounting.service.UserInfoServiceImpl;

import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * UserControllerTest.
 */
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @InjectMocks UserController userController;
    MockMvc mockMvc;
    @Mock UserInfoServiceImpl mockService;
    @Mock ServiceToWebConverter mockConverter;

    @BeforeEach
    void setup() {
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
        Long userId = 1L;
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
        Long userId = -100L;
        doThrow(new InvalidParameterException(String.format("user %s was not found", userId)))
            .when(mockService)
            .getUserInfoByUserId(ArgumentMatchers.longThat(argument -> argument <= 0));
        // act  assert
        mockMvc.perform(get("/v1.0/users/" + userId))
            .andExpect(status().is4xxClientError())
            .andExpect(content().contentType("application/json"))
            .andExpect(content().string(
                "{\"code\":\"INVALID_PARAMETER\",\"errorType\":\"Client\",\"message\":\"userId -100 is invalid\",\"statusCode\":400}"));
        verify(mockService, never()).getUserInfoByUserId(anyLong());

    }
}