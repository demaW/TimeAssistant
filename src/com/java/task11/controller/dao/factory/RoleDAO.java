package com.java.task11.controller.dao.factory;

import com.java.task11.model.Role;

import java.util.List;

public interface RoleDAO {
	public Role getByPrimaryKey(int roleId) throws DAOException;

	public List<Role> selectAll() throws DAOException;

	public List<Role> select(String whereStatement)
			throws DAOException;

	public long selectCount() throws DAOException;

	public long selectCount(String whereStatement)
			throws DAOException;

	public int update(Role obj) throws DAOException;

	public int insert(Role obj) throws DAOException;

	public int delete(Role obj) throws DAOException;
}
