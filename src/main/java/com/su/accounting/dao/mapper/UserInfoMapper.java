package com.su.accounting.dao.mapper;

import com.su.accounting.entity.persistence.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserInfoMapper {
    @Select("select id, username, password, create_time, update_time from accounting_userinfo where id = #{id}")
    UserInfo getUserInfoByUserId(@Param("id") Long id);
}
