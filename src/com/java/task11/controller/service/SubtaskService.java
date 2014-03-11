package com.java.task11.controller.service;

import com.java.task11.controller.dao.DaoFactory;
import com.java.task11.model.Subtask;

import java.util.List;

/**
 * @author nlelyak
 * @version 1.00 2014-03-05
 */
public class SubtaskService implements IBaseService<Subtask> {

    @Override
    public Subtask getByID(Integer id) {
        return DaoFactory.getInstance().getSubtaskDao().getByID(id);
    }

    @Override
    public void save(Subtask element) {
        DaoFactory.getInstance().getSubtaskDao().save(element);
    }

    @Override
    public void update(Subtask element) {
        DaoFactory.getInstance().getSubtaskDao().update(element);
    }

    @Override
    public void delete(Subtask element) {
        DaoFactory.getInstance().getSubtaskDao().delete(element);
    }

    @Override
    public List<Subtask> getListOfObjects() {
        return DaoFactory.getInstance().getSubtaskDao().getListOfObjects();
    }
}
