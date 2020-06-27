package com.meditation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.meditation.dao.entity.User;


@Mapper
public interface UserDao {

    List<User> getUsers();

    User getUser(@Param("userId") String userId);
}
