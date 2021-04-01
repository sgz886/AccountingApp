package com.su.accounting.dao;

import com.su.accounting.dao.mapper.UserInfoMapper;
import com.su.accounting.entity.persistence.UserInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * UserInfoDaoImpl.
 */
@Repository
public class UserInfoDaoImpl implements UserInfoDao {
    private final UserInfoMapper userInfoMapper;

    @Autowired
    public UserInfoDaoImpl(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public UserInfo getUserInfoByUserId(Long userId) {
        return userInfoMapper.getUserInfoByUserId(userId);
    }

    @Override
    public void createNewUser(String username, String password) {

    }
}
