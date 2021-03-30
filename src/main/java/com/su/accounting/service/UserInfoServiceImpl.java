package com.su.accounting.service;

import com.su.accounting.converter.PersistToService.PersisToServiceConverter;
import com.su.accounting.dao.UserInfoDAO;
import com.su.accounting.exception.ResourceNotFoundException;
import com.su.accounting.entity.service.UserInfo;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    private final UserInfoDAO userInfoDAO;
    private final PersisToServiceConverter persisToServiceConverter;

    @Autowired
    public UserInfoServiceImpl(final UserInfoDAO userInfoDAO, final PersisToServiceConverter persisToServiceConverter) {
        this.userInfoDAO = userInfoDAO;
        this.persisToServiceConverter = persisToServiceConverter;
    }

    @Override
    public UserInfo getUserInfoByUserId(Long userId) {
        val userInfo = Optional.ofNullable(userInfoDAO.getUserInfoByUserId(userId))
                               .orElseThrow(()->new ResourceNotFoundException(String.format("user %s was not found",userId)));
        return persisToServiceConverter.convert(userInfo);
    }
}
