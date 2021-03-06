package com.su.accounting.entity.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * entity/persistence/UserInfo.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class  UserInfo {
    private Long id;
    private String username;
    private String password;
    private LocalDate createTime;
    private LocalDate updateTime;

}
