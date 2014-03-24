package com.java.task11.controller.service;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.dao.factory.DAOFactory;
import com.java.task11.model.Task;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import java.util.List;

public class TaskService implements IBaseService<Task> {
    private static Logger log = Logger.getLogger(TaskService.class);

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

    public void delete(int taskId, HttpServlet servlet) {
        try {
            Task task = getByID(taskId);
            delete(task, servlet);
        } catch (DAOException e) {
            log.error(e);
        }
    }

    public void delete(Task task, HttpServlet servlet) {
        try {
            delete(task);
        } catch (DAOException e) {
            log.error(e);
        }
    }
}
