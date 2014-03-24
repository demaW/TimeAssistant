package com.java.task11.webapp.manager;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.service.ProjectService;
import com.java.task11.model.Project;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author nlelyak
 * @version 1.00 2014-03-21
 */
@WebServlet("/manager/projectstable")
@MultipartConfig
public class ProjectsTableServlet extends HttpServlet {
    private static Logger log = Logger.getLogger(ProjectsTableServlet.class);
    private ProjectService projectService;
    private List<Project> projects;

    @Override
    public void init() throws ServletException {
        projectService = new ProjectService();
        updateTable();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!request.getParameter("project_id").isEmpty()) {
            request.getRequestDispatcher("/pages/manager/project.jsp").forward(request, response);
            return;
        }
        updateTable();
        request.setAttribute("projects", projects);
        request.getRequestDispatcher("/pages/manager/projectsTable.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void updateTable() {
        try {
            projects = projectService.getListOfObjects();
        } catch (DAOException e) {
            log.error(e);
        }
    }
}
