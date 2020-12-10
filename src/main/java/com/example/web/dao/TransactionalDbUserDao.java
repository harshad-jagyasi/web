package com.example.web.dao;

import com.example.web.entity.DbUser;

import java.util.List;

public interface TransactionalDbUserDao {
    List<DbUser> getAllUser();
}
