package com.java.task11.controller.service;

import java.util.List;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.dao.factory.DAOFactory;
import com.java.task11.model.Task;

public class TaskService implements IBaseService<Task> {

	@Override
	public Task getByID(Integer id) throws DAOException {
		return DAOFactory.getInstance().getTaskDAO().getByPrimaryKey(id);
	}

	@Override
	public void save(Task element) throws DAOException {
		DAOFactory.getInstance().getTaskDAO().insert(element);
	}

	@Override
	public void update(Task element) throws DAOException {
		DAOFactory.getInstance().getTaskDAO().update(element);
	}

	@Override
	public void delete(Task element) throws DAOException {
		DAOFactory.getInstance().getTaskDAO().delete(element);
	}

	@Override
	public List<Task> getListOfObjects() throws DAOException {
		return DAOFactory.getInstance().getTaskDAO().selectAll();
	}

	public List<Task> getByEmployeeId(Integer id) throws DAOException {
		return DAOFactory.getInstance().getTaskDAO().getByEmployeeId(id);
	}
	
	public List<Task> getByProjectId(Integer projectId) throws DAOException {
		return DAOFactory.getInstance().getTaskDAO().getByProjectId(projectId);
	}
}
