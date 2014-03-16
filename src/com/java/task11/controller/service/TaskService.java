package com.java.task11.controller.service;

import com.java.task11.controller.dao2.DaoFactory;
import com.java.task11.model.Task;

import java.util.List;

/**
 * @author nlelyak
 * @version 1.00 2014-03-05
 */
public class TaskService implements IBaseService<Task> {

    @Override
    public Task getByID(Integer id) {
        return DaoFactory.getInstance().getTaskDao().getByID(id);
    }

    @Override
    public void save(Task element) {
        DaoFactory.getInstance().getTaskDao().save(element);
    }

    @Override
    public void update(Task element) {
        DaoFactory.getInstance().getTaskDao().update(element);
    }

    @Override
    public void delete(Task element) {
        DaoFactory.getInstance().getTaskDao().delete(element);
    }

    @Override
    public List<Task> getListOfObjects() {
        return DaoFactory.getInstance().getTaskDao().getListOfObjects();
    }
}
