package com.su.accounting.entity.service;

import lombok.Builder;
import lombok.Data;

/**
 * entity/service/UserInfo.
 */
@Data
@Builder
public class UserInfo {
    private Long id;
    private String username;
    private String password;
}
