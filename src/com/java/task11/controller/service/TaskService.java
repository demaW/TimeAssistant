package com.java.task11.controller.service;

import java.util.ArrayList;
import java.util.List;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.dao.factory.DAOFactory;
import com.java.task11.controller.dao2.DaoFactory;
import com.java.task11.model.Task;

/**
 * @author nlelyak
 * @version 1.00 2014-03-05
 */
public class TaskService implements IBaseService<Task> {

	@Override
	public Task getByID(Integer id) {
		Task task = null;
		try {
			task = DAOFactory.getInstance().getTaskDAO().getByPrimaryKey(id);
		} catch (DAOException e) {
			e.printStackTrace();
		}

		return task;
	}

	@Override
	public void save(Task element) {
		try {
			DAOFactory.getInstance().getTaskDAO().insert(element);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Task element) {
		DaoFactory.getInstance().getTaskDao().update(element);
	}

	@Override
	public void delete(Task element) {
		DaoFactory.getInstance().getTaskDao().delete(element);
	}

	@Override
	public List<Task> getListOfObjects() {
		return DaoFactory.getInstance().getTaskDao().getListOfObjects();
	}

	public List<Task> getByEmployeeId(Integer id) {

		List<Task> tasks = new ArrayList<>();
		try {
			tasks = DAOFactory.getInstance().getTaskDAO().getByEmployeeId(id);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		return tasks;
	}
}
