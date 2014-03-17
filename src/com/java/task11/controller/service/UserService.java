package com.java.task11.controller.service;

import java.util.List;

import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.dao.factory.DAOFactory;
import com.java.task11.model.User;
import com.java.task11.webapp.LoginServlet;

public class UserService implements IBaseService<User> {
	private static Logger log = Logger.getLogger(LoginServlet.class);

	@Override
	public User getByID(Integer id) {
		try {
			return DAOFactory.getInstance().getUserDAO()
					.getByPrimaryKey(id);
		} catch (DAOException e) {
			log.error(e);
			return null;
		}
	}

	@Override
	public void save(User element) {
		try {
			DAOFactory.getInstance().getUserDAO().insert(element);
		} catch (DAOException e) {
			log.error(e);
		}
		// DAOFactory.getInstance().getEmployeeDao().save(element);
	}

	@Override
	public void update(User element) {
		try {
			DAOFactory.getInstance().getUserDAO().update(element);
		} catch (DAOException e) {
			log.error(e);
		}
		// DaoFactory.getInstance().getEmployeeDao().update(element);
	}

	@Override
	public void delete(User element) {
		try {
			DAOFactory.getInstance().getUserDAO().delete(element);
		} catch (DAOException e) {
			log.error(e);
		}
		// DaoFactory.getInstance().getEmployeeDao().delete(element);
	}

	@Override
	public List<User> getListOfObjects() {
		try {
			return DAOFactory.getInstance().getUserDAO().selectAll();
		} catch (DAOException e) {
			log.error(e);
			return null;
		}
		// return DaoFactory.getInstance().getEmployeeDao().getListOfObjects();
	}

	public User getByEmail(String email) {
		

		try {
			return DAOFactory.getInstance().getUserDAO().getByEmail(email)
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
