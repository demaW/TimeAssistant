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

@WebServlet("/pages/manager/udapteprojects")
public class UpdateProject extends HttpServlet {
    private static Logger log = Logger.getLogger(UpdateProject.class);
    private ProjectService projectService;
    private Project project;

    @Override
    public void init() throws ServletException {
        projectService = new ProjectService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        if (request.getParameter("update") != null) {
            updateProject(request, response);
        } else if (request.getParameter("delete") != null) {
            deleteProject(request, response);
        } else if (request.getParameter("project_id") != null) {
            response.sendRedirect("/pages/manager/project");
        }
    }

    private void deleteProject(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("delete"));
            project = projectService.getByID(id);
            projectService.delete(project);
        } catch (DAOException e) {
            log.error(e);
        }
        response.sendRedirect("/pages/manager/projects");
    }

    private void updateProject(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String projectName = request.getParameter("name");
            String description = request.getParameter("description");
            String notes = request.getParameter("notes");

            int id = Integer.parseInt(request.getParameter("update"));
            project = projectService.getByID(id);

            if (project != null) {
                project.setProjectName(projectName);
                project.setDescription(description);
                project.setNotes(notes);
            }

            projectService.update(project);
        } catch (DAOException e) {
            log.error(e);
        }
        response.sendRedirect("/pages/manager/projects");
    }

}
