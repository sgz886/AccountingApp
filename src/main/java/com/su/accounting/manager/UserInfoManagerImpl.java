package com.su.accounting.manager;

import com.su.accounting.converter.PersistToService.PersisToServiceConverter;
import com.su.accounting.dao.UserInfoDAO;
import com.su.accounting.exception.ResourceNotFoundException;
import com.su.accounting.model.service.UserInfo;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoManagerImpl implements UserInfoManager{
    private final UserInfoDAO userInfoDAO;
    private final PersisToServiceConverter persisToServiceConverter;

    @Autowired
    public UserInfoManagerImpl(final UserInfoDAO userInfoDAO, final PersisToServiceConverter persisToServiceConverter) {
        this.userInfoDAO = userInfoDAO;
        this.persisToServiceConverter = persisToServiceConverter;
    }

    @Override
    public UserInfo getUserInfoByUserId(Long userId) {
        val userInfo = Optional.ofNullable(userInfoDAO.getUserInfoById(userId))
                               .orElseThrow(()->new ResourceNotFoundException(String.format("user %s was not found",userId)));
        return persisToServiceConverter.convert(userInfo);
    }
}
