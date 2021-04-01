package com.su.accounting.entity.web;

import lombok.Builder;
import lombok.Data;

/**
 * entity/web/UserInfo.
 */
@Data
@Builder
public class UserInfo {
    private Long id;
    private String username;
    private String password;
}
