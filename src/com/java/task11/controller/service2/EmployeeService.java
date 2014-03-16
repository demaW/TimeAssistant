package com.java.task11.controller.service2;

import com.java.task11.controller.dao2.DaoFactory;
import com.java.task11.model.User;

import javax.servlet.http.HttpServlet;
import java.util.List;

/**
 * @author nlelyak
 * @version 1.00 2014-03-05
 */
public class EmployeeService implements IBaseService<User> {

    @Override
    public User getByID(Integer id) {
        return DaoFactory.getInstance().getEmployeeDao().getByID(id);
    }

    @Override
    public void save(User element) {
        DaoFactory.getInstance().getEmployeeDao().save(element);
    }

    @Override
    public void update(User element) {
        DaoFactory.getInstance().getEmployeeDao().update(element);
    }

    @Override
    public void delete(User element) {
        DaoFactory.getInstance().getEmployeeDao().delete(element);
    }

    @Override
    public List<User> getListOfObjects() {
        return DaoFactory.getInstance().getEmployeeDao().getListOfObjects();
    }

    public User getByEmail(String email) {
        List<User> userList = getListOfObjects();
        for (User empl : userList) {
            if (empl.getEmail().equalsIgnoreCase(email)) return empl;
        }
        return null;
    }

    public void delete(Integer userId, HttpServlet servlet) {
        User empl = getByID(userId);
        delete(empl, servlet);
    }

    public void delete(User empl, HttpServlet servlet) {
        delete(empl);
    }
}
