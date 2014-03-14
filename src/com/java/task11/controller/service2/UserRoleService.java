package com.java.task11.controller.service2;

import com.java.task11.controller.dao2.DaoFactory;
import com.java.task11.model.UserRole;

import java.util.List;

/**
 * @author nlelyak
 * @version 1.00 2014-03-05
 */
public class UserRoleService implements IBaseService<UserRole> {

    @Override
    public UserRole getByID(Integer id) {
        return DaoFactory.getInstance().getRoleDao().getByID(id);
    }

    @Override
    public void save(UserRole element) {
        DaoFactory.getInstance().getRoleDao().save(element);
    }

    @Override
    public void update(UserRole element) {
        DaoFactory.getInstance().getRoleDao().update(element);
    }

    @Override
    public void delete(UserRole element) {
        DaoFactory.getInstance().getRoleDao().delete(element);
    }

    @Override
    public List<UserRole> getListOfObjects() {
        return DaoFactory.getInstance().getRoleDao().getListOfObjects();
    }
}
