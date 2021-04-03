package com.su.accounting.converter.service2web;

import com.su.accounting.entity.web.UserInfo;

import com.google.common.base.Converter;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 *  ServiceToWebConverter.
 */
@Component
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ServiceToWebConverter extends Converter<com.su.accounting.entity.service.UserInfo, UserInfo> {
    @Override
    protected UserInfo doForward(@NotNull com.su.accounting.entity.service.UserInfo userInfo) {
        return UserInfo.builder()
                       .id(userInfo.getId())
                       .username(userInfo.getUsername())
                       .build();
    }

    @Override
    protected com.su.accounting.entity.service.UserInfo doBackward(@NotNull UserInfo userInfo) {
        return com.su.accounting.entity.service.UserInfo.builder()
                       .id(userInfo.getId())
                       .username(userInfo.getUsername())
                       .password(userInfo.getPassword())
                       .build();
    }
}
