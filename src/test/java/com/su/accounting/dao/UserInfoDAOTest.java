package com.su.accounting.dao;

import com.su.accounting.dao.mapper.UserInfoMapper;
import com.su.accounting.entity.persistence.UserInfo;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserInfoDAOTest {
    @InjectMocks UserInfoDAOImpl userInfoDAO;
    @Mock UserInfoMapper mockMapper;

    // @BeforeEach
    // void setup() {
    //     userInfoDAO = new UserInfoDAOImpl(mockMapper);
    // }

    @Test
    void testGetUserInfoByUserId() {
        // arrange
        val userId = 1000L;
        val username = "accounting_admin";
        val password = "accounting";
        val createTime = LocalDate.now();
        val userInfo = UserInfo.builder()
                               .id(userId)
                               .username(username)
                               .password(password)
                               .createTime(createTime)
                               .build();
        doReturn(userInfo).when(mockMapper).getUserInfoByUserId(userId);
        // action
        UserInfo result = userInfoDAO.getUserInfoByUserId(userId);
        //assert
        assertEquals(userInfo,result);
        verify(mockMapper).getUserInfoByUserId(userId);
    }
}
