package com.java.task11.controller.service;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.dao.factory.DAOFactory;
import com.java.task11.model.Project;
import com.java.task11.webapp.LoginServlet;
import org.apache.log4j.Logger;

import java.util.List;


public class ProjectService implements IBaseService<Project> {
	private static Logger log = Logger.getLogger(LoginServlet.class);

	@Override
	public Project getByID(Integer id) {
		try {
			return DAOFactory.getInstance().getProjectDAO()
					.getByPrimaryKey(id);
		} catch (DAOException e) {
			log.error(e);
			return null;
		}
	}

	@Override
	public void save(Project element) {
		try {
			DAOFactory.getInstance().getProjectDAO().insert(element);
		} catch (DAOException e) {
			log.error(e);
		}
	}

	@Override
	public void update(Project element) {
		try {
			DAOFactory.getInstance().getProjectDAO().update(element);
		} catch (DAOException e) {
			log.error(e);
		}		
	}

	@Override
	public void delete(Project element) {
		try {
			DAOFactory.getInstance().getProjectDAO().delete(element);
		} catch (DAOException e) {
			log.error(e);
		}
		
	}

	@Override
	public List<Project> getListOfObjects() {
		try {
			return DAOFactory.getInstance().getProjectDAO().selectAll();
		} catch (DAOException e) {
			log.error(e);
			return null;
		}
	}

  
}
