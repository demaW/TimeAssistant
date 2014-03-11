package com.java.task11.controller.service;

import com.java.task11.controller.dao.DaoFactory;
import com.java.task11.model.Employee;

import javax.servlet.http.HttpServlet;
import java.util.List;

/**
 * @author nlelyak
 * @version 1.00 2014-03-05
 */
public class EmployeeService implements IBaseService<Employee> {

    @Override
    public Employee getByID(Integer id) {
        return DaoFactory.getInstance().getEmployeeDao().getByID(id);
    }

    @Override
    public void save(Employee element) {
        DaoFactory.getInstance().getEmployeeDao().save(element);
    }

    @Override
    public void update(Employee element) {
        DaoFactory.getInstance().getEmployeeDao().update(element);
    }

    @Override
    public void delete(Employee element) {
        DaoFactory.getInstance().getEmployeeDao().delete(element);
    }

    @Override
    public List<Employee> getListOfObjects() {
        return DaoFactory.getInstance().getEmployeeDao().getListOfObjects();
    }

    public Employee getByEmail(String email) {
        List<Employee> employeeList = getListOfObjects();
        for (Employee empl : employeeList) {
            if (empl.getEmail().equalsIgnoreCase(email)) return empl;
        }
        return null;
    }

    public void delete(Integer userId, HttpServlet servlet) {
        Employee empl = getByID(userId);
        delete(empl, servlet);
    }

    public void delete(Employee empl, HttpServlet servlet) {
        delete(empl);
    }
}
