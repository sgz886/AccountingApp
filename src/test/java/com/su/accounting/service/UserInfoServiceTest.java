package com.su.accounting.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

import com.su.accounting.converter.persist2service.PersisToServiceConverter;
import com.su.accounting.dao.UserInfoDao;
import com.su.accounting.entity.persistence.UserInfo;
import com.su.accounting.exception.ResourceNotFoundException;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;

class UserInfoServiceTest {
    UserInfoService userInfoService;
    @Mock UserInfoDao mockDao;

    @BeforeEach
    void setUp() throws Exception {
        openMocks(this).close();
        userInfoService = new UserInfoServiceImpl(mockDao, new PersisToServiceConverter());
    }

    @Test
    void testGetUserInfoByUserId() {
        // arrange
        val userId = 1L;
        val username = "accounting_admin";
        val password = "accounting";
        val createTime = LocalDate.now();

        val userInfo = UserInfo.builder()
                           .id(userId)
                           .username(username)
                           .password(password)
                           .createTime(createTime)
                           .build();
        doReturn(userInfo).when(mockDao).getUserInfoByUserId(userId);

        // act
        val result = userInfoService.getUserInfoByUserId(userId);

        // assertEquals(userId, result.getId());
        // assertEquals("accounting_admin", result.getUsername());
        // assertEquals("accounting", result.getPassword());

        // assertj
        assertThat(result).isNotNull()
            .hasFieldOrPropertyWithValue("id", userId)
            .hasFieldOrPropertyWithValue("username", username)
            .hasFieldOrPropertyWithValue("password", password);
        verify(mockDao, times(1)).getUserInfoByUserId(userId);
    }

    @Test
    void testGetUserInfoByUserIdWithInvalidUserId() {
        // arrange
        val userId = -1L;
        doReturn(null).when(mockDao).getUserInfoByUserId(userId);
        // act and assert
        assertThrows(ResourceNotFoundException.class,
            () -> userInfoService.getUserInfoByUserId(userId));
        verify(mockDao).getUserInfoByUserId(userId);
    }

    /*
    @Test
    void testListAddMethod() {
        // arrange
        //You can mock concrete classes, not just interfaces
        LinkedList mockedList = mock(LinkedList.class);

        //stubbing
        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());
        doThrow(new RuntimeException()).when(mockedList).clear();
        mockedList.clear();
        //following prints "first"
        System.out.println(mockedList.get(0));

        //following throws runtime exception
        // System.out.println(mockedList.get(1));

        //following prints "null" because get(999) was not stubbed
        System.out.println(mockedList.get(999));

        //Although it is possible to verify a stubbed invocation, usually it's just redundant
        //If your code cares what get(0) returns, then something else breaks (often even before verify() gets executed).
        //If your code doesn't care what get(0) returns, then it should not be stubbed.
        verify(mockedList).get(0);
    }
     */
}
