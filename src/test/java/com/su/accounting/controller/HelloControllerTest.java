package com.su.accounting.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * test hello controller.
 */
public class HelloControllerTest {
    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
    }

    @Test
    void testSayHello() throws Exception {
        // arrange act assert
        mockMvc.perform(get("/v1.0/greeting").param("name", "test"))
            .andExpect(status().isOk())
            .andExpect(content().string("{\"id\":1,\"name\":\"Hello, test\"}"));
    }
}
