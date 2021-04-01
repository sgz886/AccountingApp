package com.su.accounting.dao;

import com.su.accounting.entity.persistence.UserInfo;

/**
 * UserInfoDao.
 */
public interface UserInfoDao {
    /**
     * Get user information by user id.
     *
     * @param userId the specific user id.
     */
    UserInfo getUserInfoByUserId(Long userId);

    /**
     * create new user.
     *
     * @param username username
     * @param password password
     */
    void createNewUser(String username, String password);
}
