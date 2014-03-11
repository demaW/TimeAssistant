package com.java.task11.controller.service;

import com.java.task11.controller.dao.DaoFactory;
import com.java.task11.model.Role;

import java.util.List;

/**
 * @author nlelyak
 * @version 1.00 2014-03-05
 */
public class RoleService implements IBaseService<Role> {

    @Override
    public Role getByID(Integer id) {
        return DaoFactory.getInstance().getRoleDao().getByID(id);
    }

    @Override
    public void save(Role element) {
        DaoFactory.getInstance().getRoleDao().save(element);
    }

    @Override
    public void update(Role element) {
        DaoFactory.getInstance().getRoleDao().update(element);
    }

    @Override
    public void delete(Role element) {
        DaoFactory.getInstance().getRoleDao().delete(element);
    }

    @Override
    public List<Role> getListOfObjects() {
        return DaoFactory.getInstance().getRoleDao().getListOfObjects();
    }
}
