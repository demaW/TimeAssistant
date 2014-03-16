package com.java.task11.controller.dao2;

import com.java.task11.model.User;

/**
 * @author nlelyak
 * @version 1.00 2014-03-05
 */
public class EmployeeDao extends ElementDAOImpl<User> {
    public EmployeeDao() {
        super(User.class);
    }
}
