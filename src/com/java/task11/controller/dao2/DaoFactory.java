package com.java.task11.controller.dao2;

/**
 * @author nlelyak
 * @version 1.00 2014-03-05
 */
public class DaoFactory {

    private static DaoFactory instance = null;

    private EmployeeDao employeeDao = null;
    private RoleDao roleDao = null;
    private ProjectDao projectDao = null;
    private TeamDao teamDao = null;
    private TaskDao taskDao = null;

    public DaoFactory() {
        employeeDao = new EmployeeDao();
        roleDao = new RoleDao();
        projectDao = new ProjectDao();
        teamDao = new TeamDao();
        taskDao = new TaskDao();
    }

    public static synchronized DaoFactory getInstance() {
        if (instance == null) {
            instance = new DaoFactory();
        }
        return instance;
    }

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public ProjectDao getProjectDao() {
        return projectDao;
    }

    public TeamDao getTeamDao() {
        return teamDao;
    }

    public TaskDao getTaskDao() {
        return taskDao;
    }

}
