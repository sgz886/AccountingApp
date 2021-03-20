package com.su.accounting.dao;

import com.su.accounting.dao.mapper.UserInfoMapper;
import com.su.accounting.model.persistence.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoDAOImpl implements UserInfoDAO {
    private final UserInfoMapper userInfoMapper;

    @Autowired
    public UserInfoDAOImpl(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public UserInfo getUserInfoById(Long userId) {
        return userInfoMapper.getUserInfoByUserId(userId);
    }

    @Override
    public void createNewUser(String username, String password) {

    }
}
