package com.su.accounting.converter.persist2service;

import com.su.accounting.entity.service.UserInfo;

import com.google.common.base.Converter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * A PersisToServiceConverter.
 */
@Component
@NoArgsConstructor
public class PersisToServiceConverter extends Converter<com.su.accounting.entity.persistence.UserInfo, UserInfo> {
    @Override protected UserInfo doForward(com.su.accounting.entity.persistence.UserInfo userInfo) {
        return UserInfo.builder()
                   .id(userInfo.getId())
                   .username(userInfo.getUsername())
                   .password(userInfo.getPassword())
                   .build();
    }

    @Override protected com.su.accounting.entity.persistence.UserInfo doBackward(UserInfo userInfo) {
        return com.su.accounting.entity.persistence.UserInfo.builder()
                   .id(userInfo.getId())
                   .username(userInfo.getUsername())
                   .password(userInfo.getPassword())
                   .build();
    }
}