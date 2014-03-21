package com.java.task11.controller.service;

import java.util.List;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.dao.factory.DAOFactory;
import com.java.task11.model.Team;

public class TeamService implements IBaseService<Team> {

	@Override
	public Team getByID(Integer id) throws DAOException {
		return DAOFactory.getInstance().getTeamDAO().getByPrimaryKey(id);
	}

	@Override
	public void save(Team element) throws DAOException {
		DAOFactory.getInstance().getTeamDAO().insert(element);
	}

	@Override
	public void update(Team element) throws DAOException {
		DAOFactory.getInstance().getTeamDAO().update(element);
	}

	@Override
	public void delete(Team element) throws DAOException {
		DAOFactory.getInstance().getTeamDAO().delete(element);
	}

	@Override
	public List<Team> getListOfObjects() throws DAOException {
		return DAOFactory.getInstance().getTeamDAO().selectAll();
	}

	public List<Team> getByEmployeeId(Integer employeeId) throws DAOException {
		return DAOFactory.getInstance().getTeamDAO()
				.getByEmployeeId(employeeId);
	}

	public List<Team> getByProjectId(Integer projectId) throws DAOException {
		return DAOFactory.getInstance().getTeamDAO().getByProjectId(projectId);
	}
}
