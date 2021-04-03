package com.su.accounting.converter.persist2service;

import com.su.accounting.entity.service.UserInfo;

import com.google.common.base.Converter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * A PersisToServiceConverter.
 */
@Component
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PersisToServiceConverter extends Converter<com.su.accounting.entity.persistence.UserInfo, UserInfo> {
    @Override protected UserInfo doForward(@NotNull com.su.accounting.entity.persistence.UserInfo userInfo) {
        return UserInfo.builder()
                   .id(userInfo.getId())
                   .username(userInfo.getUsername())
                   .password(userInfo.getPassword())
                   .build();
    }

    @Override protected com.su.accounting.entity.persistence.UserInfo doBackward(@NotNull UserInfo userInfo) {
        return com.su.accounting.entity.persistence.UserInfo.builder()
                   .id(userInfo.getId())
                   .username(userInfo.getUsername())
                   .password(userInfo.getPassword())
                   .build();
    }
}
