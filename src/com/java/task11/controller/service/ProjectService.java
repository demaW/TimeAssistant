package com.java.task11.controller.service;

import java.util.List;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.dao.factory.DAOFactory;
import com.java.task11.model.Project;


public class ProjectService implements IBaseService<Project> {

	@Override
	public Project getByID(Integer id) throws DAOException {
			return DAOFactory.getInstance().getProjectDAO()
					.getByPrimaryKey(id);
	}

	@Override
	public void save(Project element) throws DAOException {
			DAOFactory.getInstance().getProjectDAO().insert(element);
	}

	@Override
	public void update(Project element) throws DAOException {
			DAOFactory.getInstance().getProjectDAO().update(element);
	}

	@Override
	public void delete(Project element) throws DAOException {
			DAOFactory.getInstance().getProjectDAO().delete(element);
		
	}

	@Override
	public List<Project> getListOfObjects() throws DAOException {
			return DAOFactory.getInstance().getProjectDAO().selectAll();
	}
	
	public List<Project> getByProjectName(String projectName) throws DAOException {
		return DAOFactory.getInstance().getProjectDAO().getByProjectName(projectName);
	}

  
}
