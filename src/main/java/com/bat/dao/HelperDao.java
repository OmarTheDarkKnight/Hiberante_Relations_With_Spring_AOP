package com.bat.dao;

import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class HelperDao {
    protected EntityManager em;

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    protected Session getCurrentSession(){
        return em.unwrap(Session.class);
    }

    protected org.hibernate.Query hibernateQuery(String query){
        return getCurrentSession().createSQLQuery(query);
    }

    protected org.hibernate.Query hibernateQuery(String query, Class dto){
        return getCurrentSession().createSQLQuery(query).setResultTransformer(Transformers.aliasToBean(dto));
    }
}