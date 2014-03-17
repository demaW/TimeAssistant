package com.java.task11.controller.dao.factory;

import java.sql.Connection;

public abstract class DAOFactory {
	private static DAOFactory instance;

	public static DAOFactory getInstance() throws DAOException {
		try {
			if (null == instance) {
				instance = (DAOFactory) Class
						.forName(
								"com.java.task11.controller.dao.implement.JDBCDAOFactory")
						.newInstance();
			}
		} catch (Exception e) {
			throw new DAOException("Could not create the DAOFactory instance",
					e);
		}

		return instance;
	}

	public abstract UserDAO getUserDAO();

	public abstract UserDAO getUserDAO(Connection conn);

	public abstract ProjectDAO getProjectDAO();

	public abstract ProjectDAO getProjectDAO(Connection conn);

	public abstract RoleDAO getRoleDAO();

	public abstract RoleDAO getRoleDAO(Connection conn);

	public abstract TaskDAO getTaskDAO();

	public abstract TaskDAO getTaskDAO(Connection conn);

	public abstract TeamDAO getTeamDAO();

	public abstract TeamDAO getTeamDAO(Connection conn);
}
