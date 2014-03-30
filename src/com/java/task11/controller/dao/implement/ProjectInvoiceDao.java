package com.java.task11.controller.dao.implement;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.model.ProjectInvoice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProjectInvoiceDao {
    protected static List<String> stdColumns = new ArrayList<String>();
    protected static List<String> allColumns = new ArrayList<String>();
    protected static String tableName = "time_assistant.tasks tasks INNER JOIN time_assistant.users users ON (tasks.employee_id = users.id)";

    static {
        stdColumns.add("tasks.title");
        stdColumns.add("tasks.real_time");
        stdColumns.add("users.first_name");
        stdColumns.add("users.last_name");
        stdColumns.add("users.position");
        stdColumns.add("users.salary_rate");
        allColumns.addAll(stdColumns);
    }

    protected Connection conn = null;

    public ProjectInvoiceDao() {
        this.conn = getConn();
    }

    public List<ProjectInvoice> getByProjectId(Integer projectId) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ProjectInvoice> ret = new ArrayList<ProjectInvoice>();

        try {
            if (null == projectId) {
                ps = getConn().prepareStatement(
                        DBUtil.selectNull(tableName, allColumns,
                                Arrays.asList("tasks.project_id"))
                );
            } else {
                ps = getConn().prepareStatement(
                        DBUtil.select(tableName, allColumns,
                                Arrays.asList("tasks.project_id"))
                );
                DBUtil.bind(ps, 1, projectId);
            }

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

    protected ProjectInvoice fromResultSet(ResultSet rs) throws SQLException {
        ProjectInvoice obj = new ProjectInvoice();

        obj.setTaskName(DBUtil.getString(rs, "title"));
        obj.setWorkedTime(DBUtil.getInt(rs, "real_time"));
        obj.setFirstName(DBUtil.getString(rs, "first_name"));
        obj.setLastName(DBUtil.getString(rs, "last_name"));
        obj.setPosition(DBUtil.getString(rs, "position"));
        obj.setSalaryRate(DBUtil.getDouble(rs, "salary_rate"));

        return obj;
    }

    protected Connection getConn() {
        return (conn == null) ? DBUtil.getConnection() : conn;
    }
}
