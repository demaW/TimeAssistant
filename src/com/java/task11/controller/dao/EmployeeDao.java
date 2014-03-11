package com.java.task11.controller.dao;

import com.java.task11.model.Employee;

/**
 * @author nlelyak
 * @version 1.00 2014-03-05
 */
public class EmployeeDao extends ElementDAOImpl<Employee> {
    public EmployeeDao() {
        super(Employee.class);
    }
}
