package com.su.accounting.controller;

import com.su.accounting.converter.ServiceToWeb.ServiceToWebConverter;
import com.su.accounting.exception.InvalidParameterException;
import com.su.accounting.manager.UserInfoManager;
import com.su.accounting.model.web.UserInfo;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
@Slf4j
// hostname:port/v1/users/...
public class UserController {
    // 1.not too many logic
    // 2.param verification ASAP

    private final UserInfoManager userInfoManager;
    private final ServiceToWebConverter serviceToWebConverter;

    @Autowired
    public UserController(final UserInfoManager userInfoManager,
                          final ServiceToWebConverter serviceToWebConverter) {
        this.userInfoManager = userInfoManager;
        this.serviceToWebConverter = serviceToWebConverter;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfo> getUserInfoByUserId(@PathVariable("id") Long userId) {
        log.debug("Get user info by user id {}", userId);

        if (userId == null || userId <= 0L) {
            throw new InvalidParameterException(String.format("userId %s is invalid",userId));
        }
        val userInfo = userInfoManager.getUserInfoByUserId(userId);
        return ResponseEntity.ok(serviceToWebConverter.convert(userInfo));

    }
}
