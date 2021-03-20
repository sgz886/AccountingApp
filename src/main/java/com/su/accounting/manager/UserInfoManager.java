package com.su.accounting.manager;

import com.su.accounting.model.service.UserInfo;
public interface UserInfoManager {
    UserInfo getUserInfoByUserId(Long userId);
}
