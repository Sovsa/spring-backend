package com.crowdcollective.spring_backend.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HibernateSessionUtil {

    private static SessionFactory sessionFactory;

    @Autowired
    public HibernateSessionUtil(SessionFactory sessionFactory) {
        HibernateSessionUtil.sessionFactory = sessionFactory;
    }

    public static Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
    
}
