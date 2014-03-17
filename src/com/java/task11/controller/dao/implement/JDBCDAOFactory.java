package com.java.task11.controller.dao.implement;

import java.sql.Connection;

import com.java.task11.controller.dao.factory.DAOFactory;
import com.java.task11.controller.dao.factory.ProjectDAO;
import com.java.task11.controller.dao.factory.RoleDAO;
import com.java.task11.controller.dao.factory.TaskDAO;
import com.java.task11.controller.dao.factory.TeamDAO;
import com.java.task11.controller.dao.factory.UserDAO;

public class JDBCDAOFactory extends DAOFactory {
	public UserDAO getUserDAO() {
		return new UserDAOImpl();
	}

	public UserDAO getUserDAO(Connection conn) {
		return new UserDAOImpl(conn);
	}

	public ProjectDAO getProjectDAO() {
		return new ProjectDAOImpl();
	}

	public ProjectDAO getProjectDAO(Connection conn) {
		return new ProjectDAOImpl(conn);
	}

	public RoleDAO getRoleDAO() {
		return new RoleDAOImpl();
	}

	public RoleDAO getRoleDAO(Connection conn) {
		return new RoleDAOImpl(conn);
	}

	public TaskDAO getTaskDAO() {
		return new TaskDAOImpl();
	}

	public TaskDAO getTaskDAO(Connection conn) {
		return new TaskDAOImpl(conn);
	}

	public TeamDAO getTeamDAO() {
		return new TeamDAOImpl();
	}

	public TeamDAO getTeamDAO(Connection conn) {
		return new TeamDAOImpl(conn);
	}
}
