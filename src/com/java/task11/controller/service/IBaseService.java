package com.java.task11.controller.service;

import java.util.List;

/**
 * @author nlelyak
 * @version 1.00 2014-03-05
 */
public interface IBaseService<T> {
    T getByID(Integer id);
    void save(T element);
    void update(T element);
    void delete(T element);
    List<T> getListOfObjects();
}
