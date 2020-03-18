package com.example.bj.mapper;

public interface UserMapper {

    public String getUserName(String loginName);

    public String getUserPwd(String loginPwd,String loginName);

}
