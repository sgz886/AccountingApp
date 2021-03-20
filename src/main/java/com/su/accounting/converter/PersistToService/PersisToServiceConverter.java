package com.su.accounting.converter.PersistToService;

import com.google.common.base.Converter;
import com.su.accounting.model.service.UserInfo;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class PersisToServiceConverter extends Converter<com.su.accounting.model.persistence.UserInfo, UserInfo> {
    @Override protected UserInfo doForward(com.su.accounting.model.persistence.UserInfo userInfo) {
        return UserInfo.builder()
                       .id(userInfo.getId())
                       .username(userInfo.getUsername())
                       .password(userInfo.getPassword())
                       .build();
    }

    @Override protected com.su.accounting.model.persistence.UserInfo doBackward(UserInfo userInfo) {
        return com.su.accounting.model.persistence.UserInfo.builder()
                       .id(userInfo.getId())
                       .username(userInfo.getUsername())
                       .password(userInfo.getPassword())
                       .build();
    }
}
