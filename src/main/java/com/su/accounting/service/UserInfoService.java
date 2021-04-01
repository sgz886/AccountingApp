package com.su.accounting.service;

import com.su.accounting.entity.service.UserInfo;

/**
 * UserInfoService.
 */
public interface UserInfoService {
    UserInfo getUserInfoByUserId(Long userId);
}
