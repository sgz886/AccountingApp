package com.su.accounting.converter;

import com.su.accounting.converter.PersistToService.PersisToServiceConverter;
import com.su.accounting.entity.persistence.UserInfo;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PersisToServiceConverterTest {
    PersisToServiceConverter converter = new PersisToServiceConverter();

    @Test
    void testDoForward() {
        //arrange
        val userId = 100L;
        val username = "df";
        val password = "werere";
        UserInfo userInfoPersi = UserInfo.builder()
                                         .id(userId)
                                         .username(username)
                                         .password(password)
                                         .createTime(LocalDate.now())
                                         .updateTime(LocalDate.now())
                                         .build();
        //act
        val result = converter.convert(userInfoPersi);

        //assert
        assertThat(result).isNotNull()
                .hasFieldOrPropertyWithValue("id", userId)
                .hasFieldOrPropertyWithValue("username", username)
                .hasFieldOrPropertyWithValue("password", password);
    }
    @Test
    void testDoBackward() {
        //arrange
        val userId = 100L;
        val username = "df";
        val password = "werere";
        val userInfoService = com.su.accounting.entity.service.UserInfo.builder()
                                         .id(userId)
                                         .username(username)
                                         .password(password)
                                         .build();
        //act
        val result = converter.reverse().convert(userInfoService);

        //assert
        assertThat(result).isNotNull()
                .hasFieldOrPropertyWithValue("id", userId)
                .hasFieldOrPropertyWithValue("username", username)
                .hasFieldOrPropertyWithValue("password", password);
    }
}
