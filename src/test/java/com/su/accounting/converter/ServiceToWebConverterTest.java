package com.su.accounting.converter;

import com.su.accounting.converter.ServiceToWeb.ServiceToWebConverter;
import com.su.accounting.entity.service.UserInfo;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ServiceToWebConverterTest {
    ServiceToWebConverter converter = new ServiceToWebConverter();

    @Test
    void testDoForward() {
        //arrange
        val userId = 100L;
        val username = "df";
        val password = "werere";
        UserInfo userInfoService = UserInfo.builder()
                                         .id(userId)
                                         .username(username)
                                         .build();
        //act
        val result = converter.convert(userInfoService);

        //assert
        assertThat(result).isNotNull()
                .hasFieldOrPropertyWithValue("id", userId)
                .hasFieldOrPropertyWithValue("username", username);
    }
    @Test
    void testDoBackward() {
        //arrange
        val userId = 100L;
        val username = "df";
        val password = "werere";
        val userInfoWeb = com.su.accounting.entity.web.UserInfo.builder()
                                      .id(userId)
                                      .username(username)
                                      .password(password)
                                      .build();
        //act
        val result = converter.reverse().convert(userInfoWeb);

        //assert
        assertThat(result).isNotNull()
                .hasFieldOrPropertyWithValue("id", userId)
                .hasFieldOrPropertyWithValue("username", username)
                .hasFieldOrPropertyWithValue("password", password);
    }
}
