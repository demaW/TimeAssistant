package com.java.task11.controller.service;

import java.util.List;

import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.dao.factory.DAOFactory;
import com.java.task11.model.Employee;
import com.java.task11.webapp.LoginServlet;

public class EmployeeService implements IBaseService<Employee> {
	private static Logger log = Logger.getLogger(LoginServlet.class);

	@Override
	public Employee getByID(Integer id) {
		try {
			return DAOFactory.getInstance().getEmployeeDAO()
					.getByPrimaryKey(id);
		} catch (DAOException e) {
			log.error(e);
			return null;
		}
	}

	@Override
	public void save(Employee element) {
		try {
			DAOFactory.getInstance().getEmployeeDAO().insert(element);
		} catch (DAOException e) {
			log.error(e);
		}
		// DAOFactory.getInstance().getEmployeeDao().save(element);
	}

	@Override
	public void update(Employee element) {
		try {
			DAOFactory.getInstance().getEmployeeDAO().update(element);
		} catch (DAOException e) {
			log.error(e);
		}
		// DaoFactory.getInstance().getEmployeeDao().update(element);
	}

	@Override
	public void delete(Employee element) {
		try {
			DAOFactory.getInstance().getEmployeeDAO().delete(element);
		} catch (DAOException e) {
			log.error(e);
		}
		// DaoFactory.getInstance().getEmployeeDao().delete(element);
	}

	@Override
	public List<Employee> getListOfObjects() {
		try {
			return DAOFactory.getInstance().getEmployeeDAO().selectAll();
		} catch (DAOException e) {
			log.error(e);
			return null;
		}
		// return DaoFactory.getInstance().getEmployeeDao().getListOfObjects();
	}

	public Employee getByEmail(String email) {
		// List<Employee> employeeList = getListOfObjects();
		// for (Employee empl : employeeList) {
		// if (empl.getEmail().equalsIgnoreCase(email))
		// return empl;
		// }
		// return null;

		try {
			return DAOFactory.getInstance().getEmployeeDAO().getByEmail(email)
					.get(0);
		} catch (DAOException | IndexOutOfBoundsException e) {
			log.error(e);
			return null;
		}
	}

	public void delete(Integer userId, HttpServlet servlet) {
		Employee empl = getByID(userId);
		delete(empl, servlet);
	}

	public void delete(Employee empl, HttpServlet servlet) {
		delete(empl);
	}
}
