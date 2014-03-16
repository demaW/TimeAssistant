package com.java.task11.controller.dao2;

import com.java.task11.controller.persistance.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nlelyak
 * @version 1.00 2014-03-05
 */
public class ElementDAOImpl<T> implements IBaseDao<T> {
    private Class<T> elementClass;

    public ElementDAOImpl(Class<T> elementClass) {
        this.elementClass = elementClass;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getByID(int id) {
        Session session = null;
        T element = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            element = (T) session.get(elementClass, id);
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
        return element;
    }

    @Override
    public void save(T element) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(element);
            transaction.commit();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
    }

    @Override
    public void update(T element) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession(); //getCurrentSession();
            Transaction transaction = session.beginTransaction();
            session.update(element);
            transaction.commit();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
    }

    @Override
    public void delete(T element) {
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(element);
            transaction.commit();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> getListOfObjects() {
        Session session = null;
        List<T> elements = new ArrayList<>();

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            elements = session.createCriteria(elementClass).list();
        } finally {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
        return elements;
    }
}
