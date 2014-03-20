package com.java.task11.controller.dao.implement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.task11.webapp.LoginServlet;

public class DBUtil {
	private final static String driver = "com.mysql.jdbc.Driver";
	private final static String url = "jdbc:mysql://localhost:3306/time_assistant?characterEncoding=utf8";
	private final static String userName = "root";
	private final static String password = "root";
	private static Connection conn = null;
	private static Logger log = Logger.getLogger(LoginServlet.class);

	public static void close(ResultSet res) {
		close(null, res);
	}

	public static void close(Statement stmt, ResultSet res) {
		try {
			if (res != null) {
				res.close();
			}
		} catch (SQLException ex) {
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException ex) {
			}
		}
	}

	protected static Connection getConnection() {
		 try {
		 Class.forName(driver);
		 conn = DriverManager.getConnection(url, userName, password);
		 } catch (ClassNotFoundException | SQLException e) {
		 log.error("problem with driver", e);
		 }
		 return conn;

	}

	protected static String getQuestionMarks(int size) {
		StringBuffer buf = new StringBuffer();

		for (int i = 0; i < size; i++) {
			if (i > 0) {
				buf.append(", ");
			}

			buf.append("?");
		}

		return buf.toString();
	}

	public static String delete(String tableName, List<String> pkColumns) {
		StringBuffer buf = new StringBuffer("DELETE FROM ").append(tableName)
				.append(" WHERE ");

		for (int i = 0; i < pkColumns.size(); i++) {
			if (i > 0) {
				buf.append(" AND ");
			}

			buf.append(pkColumns.get(i)).append(" = ?");
		}

		return buf.toString();
	}

	public static String select(String tableName, List<String> selectColumns) {
		return select(tableName, selectColumns, new ArrayList<String>());
	}

	public static String select(String tableName, List<String> selectColumns,
			List<String> whereColumns) {
		StringBuffer buf = new StringBuffer("SELECT ");

		for (int i = 0; i < selectColumns.size(); i++) {
			if (i > 0) {
				buf.append(", ");
			}

			buf.append(selectColumns.get(i));
		}

		buf.append(" FROM ").append(tableName);

		if (whereColumns.size() > 0) {
			buf.append(" WHERE ");

			for (int i = 0; i < whereColumns.size(); i++) {
				if (i > 0) {
					buf.append(" AND ");
				}

				buf.append(whereColumns.get(i)).append(" = ?");
			}
		}

		return buf.toString();
	}

	public static String selectNull(String tableName,
			List<String> selectColumns, List<String> whereColumns) {
		StringBuffer buf = new StringBuffer("SELECT ");

		for (int i = 0; i < selectColumns.size(); i++) {
			if (i > 0) {
				buf.append(", ");
			}

			buf.append(selectColumns.get(i));
		}

		buf.append(" FROM ").append(tableName);

		if (whereColumns.size() > 0) {
			buf.append(" WHERE ");

			for (int i = 0; i < whereColumns.size(); i++) {
				if (i > 0) {
					buf.append(" AND ");
				}

				buf.append(whereColumns.get(i)).append(" IS NULL");
			}
		}

		return buf.toString();
	}

	public static String update(String tableName, List<String> stdColumns,
			List<String> pkColumns) {
		StringBuffer buf = new StringBuffer("UPDATE ").append(tableName)
				.append(" SET ");

		for (int i = 0; i < stdColumns.size(); i++) {
			if (i > 0) {
				buf.append(", ");
			}

			buf.append(stdColumns.get(i)).append(" = ?");
		}

		for (int i = 0; i < pkColumns.size(); i++) {
			if (i > 0) {
				buf.append(" AND ");
			} else {
				buf.append(" WHERE ");
			}

			buf.append(pkColumns.get(i)).append(" = ?");
		}

		return buf.toString();
	}

	public static String insert(String tableName, List<String> pkColumns,
			List<String> stdColumns) {
		List<String> allColumns = new ArrayList<String>(pkColumns);
		StringBuffer buf = new StringBuffer("INSERT INTO ").append(tableName)
				.append(" ( ");

		allColumns.addAll(stdColumns);

		for (int i = 0; i < allColumns.size(); i++) {
			if (i > 0) {
				buf.append(", ");
			}

			buf.append(allColumns.get(i));
		}

		buf.append(" ) VALUES (").append(getQuestionMarks(allColumns.size()));

		return buf.append(")").toString();
	}

	public static String getString(ResultSet rs, String col)
			throws SQLException {
		return rs.getString(col);
	}

	public static int getInt(ResultSet rs, String col) throws SQLException {
		return rs.getInt(col);
	}

	public static Integer getInteger(ResultSet rs, String col)
			throws SQLException {
		int val = rs.getInt(col);

		return rs.wasNull() ? null : new Integer(val);
	}

	public static Double getDouble(ResultSet rs, String col)
			throws SQLException {
		double val = rs.getDouble(col);

		return rs.wasNull() ? null : new Double(val);
	}

	public static void bind(PreparedStatement ps, int pos, double val)
			throws SQLException {
		ps.setDouble(pos, val);
	}

	public static void bind(PreparedStatement ps, int pos, Double val)
			throws SQLException {
		if (null == val) {
			ps.setNull(pos, Types.DOUBLE);
		} else {
			ps.setDouble(pos, val.doubleValue());
		}
	}

	public static Date getDate(ResultSet rs, String col) throws SQLException {
		return rs.getTimestamp(col);
	}

	public static Time getTime(ResultSet rs, String col) throws SQLException {
		return rs.getTime(col);
	}

	public static void bind(PreparedStatement ps, int pos, int val)
			throws SQLException {
		ps.setInt(pos, val);
	}

	public static void bind(PreparedStatement ps, int pos, Integer val)
			throws SQLException {
		if (null == val) {
			ps.setNull(pos, Types.INTEGER);
		} else {
			ps.setInt(pos, val.intValue());
		}
	}

	public static void bind(PreparedStatement ps, int pos, String val)
			throws SQLException {
		if (null == val) {
			ps.setNull(pos, Types.VARCHAR);
		} else {
			ps.setString(pos, val);
		}
	}

	public static void bind(PreparedStatement ps, int pos, Date val)
			throws SQLException {
		if (null == val) {
			ps.setNull(pos, Types.TIMESTAMP);
		} else {
			ps.setTimestamp(pos, new java.sql.Timestamp(val.getTime()));
		}
	}
}
