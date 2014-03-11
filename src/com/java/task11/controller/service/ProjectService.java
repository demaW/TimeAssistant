package com.java.task11.controller.service;

import com.java.task11.controller.dao.DaoFactory;
import com.java.task11.model.Project;

import java.util.List;

/**
 * @author nlelyak
 * @version 1.00 2014-03-05
 */
public class ProjectService implements IBaseService<Project> {

    @Override
    public Project getByID(Integer id) {
        return DaoFactory.getInstance().getProjectDao().getByID(id);
    }

    @Override
    public void save(Project element) {
        DaoFactory.getInstance().getProjectDao().save(element);
    }

    @Override
    public void update(Project element) {
        DaoFactory.getInstance().getProjectDao().update(element);
    }

    @Override
    public void delete(Project element) {
        DaoFactory.getInstance().getProjectDao().delete(element);
    }

    @Override
    public List<Project> getListOfObjects() {
        return DaoFactory.getInstance().getProjectDao().getListOfObjects();
    }
}
