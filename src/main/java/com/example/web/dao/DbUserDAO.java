package com.example.web.dao;

import com.example.web.entity.DbUser;
import com.example.web.exception.NoRecordFoundException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class DbUserDAO {

    private final JdbcTemplate jdbcTemplate;

    private final DataSource dataSource;

    public DbUserDAO(DataSource dataSource){
        this.dataSource =dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final String GET_USER_PROFILE =
            "SELECT id, email, name FROM db_user WHERE email = ?";

//    public DbUser getUserIdFromDbuser(String email) throws NoRecordFoundException{
//            return jdbcTemplate.queryfor(GET_USER_PROFILE, new DbUser(), email);
//    }
}
