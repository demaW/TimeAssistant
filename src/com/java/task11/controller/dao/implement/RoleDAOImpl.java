package com.java.task11.controller.dao.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.dao.factory.RoleDAO;
import com.java.task11.model.Role;

public class RoleDAOImpl implements RoleDAO {
	protected static List<String> pkColumns = new ArrayList<String>();
	protected static List<String> stdColumns = new ArrayList<String>();
	protected static List<String> allColumns = new ArrayList<String>();
	protected static String tableName = "roles";

	static {
		pkColumns.add("role_id");
		stdColumns.add("role_name");
		allColumns.addAll(pkColumns);
		allColumns.addAll(stdColumns);
	}

	protected Connection conn = null;

	public RoleDAOImpl() {
		this(null);
	}

	public RoleDAOImpl(Connection conn) {
		this.conn = conn;
	}

	public Role getByPrimaryKey(int roleId) throws DAOException {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			int pos = 1;
			ps = getConn().prepareStatement(
					DBUtil.select(tableName, allColumns, pkColumns));
			DBUtil.bind(ps, pos++, roleId);
			rs = ps.executeQuery();

			if (rs.next()) {
				return fromResultSet(rs);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DBUtil.close(ps, rs);
		}

		return null;
	}

	public long selectCount() throws DAOException {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = getConn()
					.prepareStatement("select count(*) from " + tableName);
			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getLong(1);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DBUtil.close(ps, rs);
		}

		return 0;
	}

	public long selectCount(String whereStatement) throws DAOException {
		PreparedStatement ps = null;
		ResultSet rs = null;

		if (!whereStatement.trim().toUpperCase().startsWith("WHERE")) {
			whereStatement = " WHERE " + whereStatement;
		} else if (whereStatement.startsWith(" ") == false) {
			whereStatement = " " + whereStatement;
		}

		try {
			ps = getConn().prepareStatement(
					"select count(*) from " + tableName + whereStatement);

			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getLong(1);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DBUtil.close(ps, rs);
		}

		return 0;
	}

	public List<Role> selectAll() throws DAOException {
		List<Role> ret = new ArrayList<Role>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = getConn().prepareStatement(
					DBUtil.select(tableName, allColumns));
			rs = ps.executeQuery();

			while (rs.next())
				ret.add(fromResultSet(rs));
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DBUtil.close(ps, rs);
		}

		return ret;
	}

	public List<Role> select(String whereStatement) throws DAOException {
		List<Role> ret = new ArrayList<Role>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		if (!whereStatement.trim().toUpperCase().startsWith("WHERE")) {
			whereStatement = " WHERE " + whereStatement;
		} else if (whereStatement.startsWith(" ") == false) {
			whereStatement = " " + whereStatement;
		}

		try {
			ps = getConn().prepareStatement(
					DBUtil.select(tableName, allColumns) + whereStatement);

			rs = ps.executeQuery();

			while (rs.next())
				ret.add(fromResultSet(rs));
		} catch (SQLException e) {
			throw new DAOException("Error in select(), table = " + tableName, e);
		} finally {
			DBUtil.close(ps, rs);
		}

		return ret;
	}

	public int update(Role obj) throws DAOException {
		PreparedStatement ps = null;
		int pos = 1;

		try {
			ps = getConn().prepareStatement(
					DBUtil.update(tableName, stdColumns, pkColumns));
			pos = bindStdColumns(ps, obj, pos);
			bindPrimaryKey(ps, obj, pos);

			int rowCount = ps.executeUpdate();

			if (rowCount != 1) {
				throw new DAOException("Error updating " + obj.getClass()
						+ " in " + tableName + ", affected rows = " + rowCount);
			}

			return rowCount;
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DBUtil.close(ps, null);
		}
	}

	public int insert(Role obj) throws DAOException {
		PreparedStatement ps = null;
		int pos = 1;

		try {
			ps = getConn().prepareStatement(
					DBUtil.insert(tableName, pkColumns, stdColumns));
			pos = bindPrimaryKey(ps, obj, pos);
			bindStdColumns(ps, obj, pos);

			int rowCount = ps.executeUpdate();

			if (rowCount != 1) {
				throw new DAOException("Error inserting " + obj.getClass()
						+ " in " + tableName + ", affected rows = " + rowCount);
			}

			return rowCount;
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DBUtil.close(ps, null);
		}
	}

	public int delete(Role obj) throws DAOException {
		PreparedStatement ps = null;

		try {
			ps = getConn()
					.prepareStatement(DBUtil.delete(tableName, pkColumns));
			bindPrimaryKey(ps, obj, 1);

			int rowCount = ps.executeUpdate();

			if (rowCount != 1) {
				throw new DAOException("Error deleting " + obj.getClass()
						+ " in " + tableName + ", affected rows = " + rowCount);
			}

			return rowCount;
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DBUtil.close(ps, null);
		}
	}

	protected int bindPrimaryKey(PreparedStatement ps, Role obj, int pos)
			throws SQLException {
		DBUtil.bind(ps, pos++, obj.getRoleId());

		return pos;
	}

	protected int bindStdColumns(PreparedStatement ps, Role obj, int pos)
			throws SQLException {
		DBUtil.bind(ps, pos++, obj.getRoleName());

		return pos;
	}

	protected Role fromResultSet(ResultSet rs) throws SQLException {
		Role obj = new Role();

		obj.setRoleId(DBUtil.getInt(rs, "role_id"));
		obj.setRoleName(DBUtil.getString(rs, "role_name"));

		return obj;
	}

	protected Connection getConn() {
		return (conn == null) ? DBUtil.getConnection() : conn;
	}
}
