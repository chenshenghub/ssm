package com.itcast.Mappers;

import com.itcast.domin.User;

import java.util.List;

public interface UserMapper {

    public List<User> findAll();
    //模糊查询
    public List<User> findUserByName(String username);
    void add();

}
