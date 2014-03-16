package com.java.task11.controller.service;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.dao.factory.DAOFactory;
import com.java.task11.model.User;
import com.java.task11.webapp.LoginServlet;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import java.util.List;

public class EmployeeService implements IBaseService<User> {
	private static Logger log = Logger.getLogger(LoginServlet.class);

	@Override
	public User getByID(Integer id) {
		try {
			return DAOFactory.getInstance().getEmployeeDAO()
					.getByPrimaryKey(id);
		} catch (DAOException e) {
			log.error(e);
			return null;
		}
	}

	@Override
	public void save(User element) {
		try {
			DAOFactory.getInstance().getEmployeeDAO().insert(element);
		} catch (DAOException e) {
			log.error(e);
		}
		// DAOFactory.getInstance().getEmployeeDao().save(element);
	}

	@Override
	public void update(User element) {
		try {
			DAOFactory.getInstance().getEmployeeDAO().update(element);
		} catch (DAOException e) {
			log.error(e);
		}
		// DaoFactory.getInstance().getEmployeeDao().update(element);
	}

	@Override
	public void delete(User element) {
		try {
			DAOFactory.getInstance().getEmployeeDAO().delete(element);
		} catch (DAOException e) {
			log.error(e);
		}
		// DaoFactory.getInstance().getEmployeeDao().delete(element);
	}

	@Override
	public List<User> getListOfObjects() {
		try {
			return DAOFactory.getInstance().getEmployeeDAO().selectAll();
		} catch (DAOException e) {
			log.error(e);
			return null;
		}
		// return DaoFactory.getInstance().getEmployeeDao().getListOfObjects();
	}

	public User getByEmail(String email) {
		

		try {
			return DAOFactory.getInstance().getEmployeeDAO().getByEmail(email)
					.get(0);
		} catch (DAOException | IndexOutOfBoundsException e) {
			log.error(e);
			return null;
		}
	}

	public void delete(Integer userId, HttpServlet servlet) {
		User empl = getByID(userId);
		delete(empl, servlet);
	}

	public void delete(User empl, HttpServlet servlet) {
		delete(empl);
	}
}
