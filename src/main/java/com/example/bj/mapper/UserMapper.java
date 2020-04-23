package com.example.bj.mapper;

import org.springframework.data.repository.query.Param;

public interface UserMapper {

    public String getUserName(String loginName);

    public String getUserPwd(@Param("loginPwd")String loginPwd, @Param("loginName")String loginName);

}
