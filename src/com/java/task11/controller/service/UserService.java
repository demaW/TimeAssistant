package com.java.task11.controller.service;

import java.util.List;

import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.dao.factory.DAOFactory;
import com.java.task11.model.Team;
import com.java.task11.model.User;

public class UserService implements IBaseService<User> {
	private static Logger log = Logger.getLogger(LoginServlet.class);

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


	public List<User> getUsersByProjectId(Integer project_id) throws DAOException {
		List<Team> team = DAOFactory.getInstance().getTeamDAO().getByProjectId(project_id);
		
		List<User> usersInProject = new ArrayList<>();
		
		for (Team singleTeam : team) {
			Integer userId = singleTeam.getEmployeeId();
			User user = DAOFactory.getInstance().getUserDAO().getByPrimaryKey(userId);
			usersInProject.add(user);
		}
		
		return usersInProject;
	}
}
