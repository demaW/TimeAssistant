package com.java.task11.controller.service;

import java.util.List;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.dao.factory.DAOFactory;
import com.java.task11.model.User;

public class UserService implements IBaseService<User> {

	@Override
	public User getByID(Integer id) throws DAOException {
		return DAOFactory.getInstance().getUserDAO().getByPrimaryKey(id);
	}

	@Override
	public void save(User element) throws DAOException {
		DAOFactory.getInstance().getUserDAO().insert(element);
	}

	@Override
	public void update(User element) throws DAOException {
		DAOFactory.getInstance().getUserDAO().update(element);
	}

	@Override
	public void delete(User element) throws DAOException {
		DAOFactory.getInstance().getUserDAO().delete(element);
	}

	@Override
	public List<User> getListOfObjects() throws DAOException {
		return DAOFactory.getInstance().getUserDAO().selectAll();
	}

	public User getByEmail(String email) throws DAOException {
		return DAOFactory.getInstance().getUserDAO().getByEmail(email).get(0);
	}
	
	public List<User> getByPosition(String position) throws DAOException {
		return DAOFactory.getInstance().getUserDAO().getByPosition(position);
	}
}
