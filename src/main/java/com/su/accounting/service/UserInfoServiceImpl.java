package com.su.accounting.service;

import com.su.accounting.converter.persist2service.PersisToServiceConverter;
import com.su.accounting.dao.UserInfoDao;
import com.su.accounting.entity.service.UserInfo;
import com.su.accounting.exception.ResourceNotFoundException;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * UserInfoServiceImpl.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    private final UserInfoDao userInfoDao;
    private final PersisToServiceConverter persisToServiceConverter;

    @Autowired
    public UserInfoServiceImpl(final UserInfoDao userInfoDao, final PersisToServiceConverter persisToServiceConverter) {
        this.userInfoDao = userInfoDao;
        this.persisToServiceConverter = persisToServiceConverter;
    }

    @Override
    public UserInfo getUserInfoByUserId(Long userId) {
        val userInfo = Optional.ofNullable(userInfoDao.getUserInfoByUserId(userId))
                           .orElseThrow(() -> new ResourceNotFoundException(String.format("user %s was not found", userId)));
        return persisToServiceConverter.convert(userInfo);
    }
}
