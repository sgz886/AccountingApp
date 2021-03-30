package com.su.accounting.service;

import com.su.accounting.entity.service.UserInfo;
public interface UserInfoService {
    UserInfo getUserInfoByUserId(Long userId);
}
