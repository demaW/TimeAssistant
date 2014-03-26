package com.java.task11.webapp.manager;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.service.ProjectService;
import com.java.task11.model.Project;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/manager/projects")
public class OutProjects extends HttpServlet {
    private static Logger log = Logger.getLogger(OutProjects.class);
    private ProjectService projectService;
    private List<Project> projects;

    @Override
    public void init() throws ServletException {
        projectService = new ProjectService();
        updateTable();
    }

    private void updateTable() {
        try {
            projects = projectService.getListOfObjects();
        } catch (DAOException e) {
            log.error(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("projects", projects);
        request.getRequestDispatcher(request.getContextPath() + "/pages/manager/projects.jsp").forward(request, response);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
