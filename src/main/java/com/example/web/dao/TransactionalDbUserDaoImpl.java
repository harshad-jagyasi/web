package com.example.web.dao;

import com.example.web.entity.DbUser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static com.example.web.configuration.DbuserDBConfiguration.DB_ENTITY_MANAGER_FACTORY;
import static com.example.web.configuration.DbuserDBConfiguration.DB_TRANSACTION_MANAGER;
import static com.example.web.entity.DbUser.NAMED_QUERY_GET_ALL;

@Component
@Transactional(DB_TRANSACTION_MANAGER)
public class TransactionalDbUserDaoImpl implements  TransactionalDbUserDao{
    @PersistenceContext(unitName = DB_ENTITY_MANAGER_FACTORY)
    @Qualifier(DB_ENTITY_MANAGER_FACTORY)
    private EntityManager entityManager;

    @Override
    public List<DbUser> getAllUser() {
        return entityManager.createNamedQuery(NAMED_QUERY_GET_ALL , DbUser.class).getResultList();
    }
}
