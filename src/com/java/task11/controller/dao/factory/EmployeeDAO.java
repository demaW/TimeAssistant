package com.java.task11.controller.dao.factory;

import com.java.task11.model.Employee;

import java.util.List;

public interface EmployeeDAO {
	public Employee getByPrimaryKey(int id) throws DAOException;

	public List<Employee> selectAll() throws DAOException;

	public List<Employee> select(String whereStatement)
			throws DAOException;

	public long selectCount() throws DAOException;

	public long selectCount(String whereStatement)
			throws DAOException;

	public int update(Employee obj) throws DAOException;

	public int insert(Employee obj) throws DAOException;

	public int delete(Employee obj) throws DAOException;

	public List<Employee> getByEmail(String email) throws DAOException;

	public List<Employee> getByPosition(String position) throws DAOException;
}
