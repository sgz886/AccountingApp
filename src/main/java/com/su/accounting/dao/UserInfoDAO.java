package com.su.accounting.dao;

import com.su.accounting.model.persistence.UserInfo;

public interface UserInfoDAO {
    /**
     * Get user information by user id.
     * @param userId the specific user id.
     */
    UserInfo getUserInfoById(Long userId);

    /**
     * create new user
     * @param username
     * @param password
     */
    void createNewUser(String username, String password);
}
