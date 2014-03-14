package com.java.task11.controller.dao.implement;

import com.java.task11.controller.dao.factory.DAOFactory;
import com.java.task11.controller.dao.factory.EmployeeDAO;
import com.java.task11.controller.dao.factory.ProjectDAO;
import com.java.task11.controller.dao.factory.RoleDAO;
import com.java.task11.controller.dao.factory.TaskDAO;
import com.java.task11.controller.dao.factory.TeamDAO;

import java.sql.Connection;

public class JDBCDAOFactory extends DAOFactory {
	public EmployeeDAO getEmployeeDAO() {
		return new EmployeeDAOImpl();
	}

	public EmployeeDAO getEmployeeDAO(Connection conn) {
		return new EmployeeDAOImpl(conn);
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
