package com.su.accounting.converter.ServiceToWeb;

import com.google.common.base.Converter;
import com.su.accounting.model.web.UserInfo;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ServiceToWebConverter extends Converter<com.su.accounting.model.service.UserInfo, UserInfo> {
    @Override
    protected UserInfo doForward(com.su.accounting.model.service.UserInfo userInfo) {
        return UserInfo.builder()
                       .id(userInfo.getId())
                       .username(userInfo.getUsername())
                       .build();
    }

    @Override
    protected com.su.accounting.model.service.UserInfo doBackward(UserInfo userInfo) {
        return com.su.accounting.model.service.UserInfo.builder()
                       .id(userInfo.getId())
                       .username(userInfo.getUsername())
                       .password(userInfo.getPassword())
                       .build();
    }
}
