package com.java.task11.webapp.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.service.ProjectService;
import com.java.task11.model.Project;
/**
 * Servlet implementation class UpdateProject
 */
@WebServlet("/pages/manager/udapteprojects")
public class UpdateProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProject() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("update") != null) {
			updateProject(request, response);
		} else if (request.getParameter("delete") != null) {
			deleteProject(request, response);
		}
	}

	private void deleteProject(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("delete"));
		ProjectService proServ = new ProjectService();
		Project project = null;
		try {
			project = proServ.getByID(id);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			proServ.delete(project);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("/TimeAssistant/pages/manager/projects");
	}

	private void updateProject(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String projectName = request.getParameter("name");
		String description = request.getParameter("description");
		String notes = request.getParameter("notes");
				
		int id = Integer.parseInt(request.getParameter("update"));
		ProjectService proServ = new ProjectService();
		Project project = null;
		try {
			project = proServ.getByID(id);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		project.setProjectName(projectName);
		project.setDescription(description);
		project.setNotes(notes);
		try {
			proServ.update(project);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("/TimeAssistant/pages/manager/projects");
	}
	

}
