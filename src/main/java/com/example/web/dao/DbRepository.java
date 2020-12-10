package com.example.web.dao;

import com.example.web.entity.DbUser;
import org.springframework.data.repository.CrudRepository;


public interface DbRepository extends CrudRepository<DbUser,Integer> {
}
