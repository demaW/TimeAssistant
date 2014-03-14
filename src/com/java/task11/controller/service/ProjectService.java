package com.java.task11.controller.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.dao.factory.DAOFactory;
import com.java.task11.model.Project;
import com.java.task11.webapp.LoginServlet;
import com.sun.org.apache.regexp.internal.recompile;

/**
 * @author nlelyak
 * @version 1.00 2014-03-05
 */
public class ProjectService implements IBaseService<Project> {
	private static Logger log = Logger.getLogger(LoginServlet.class);
	@Override
	public Project getByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		
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
