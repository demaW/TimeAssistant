package com.java.task11.webapp.manager;

import java.io.IOException;
import java.sql.Time;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.service.TaskService;
import com.java.task11.controller.service.UserService;
import com.java.task11.model.Task;
import com.java.task11.model.User;

@WebServlet("/manager/addTask")
public class AddTask extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddTask() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			Integer projectId = Integer.parseInt(request
					.getParameter("project_id"));

			List<User> usersAssignedToProject = new UserService()
					.getUsersByProjectId(projectId);
			
			request.setAttribute("project_id", projectId);
			request.setAttribute("users_in_project", usersAssignedToProject);

			request.getRequestDispatcher("/pages/manager/addTask.jsp").forward(
					request, response);

		} catch (NumberFormatException | DAOException e) {
			// TODO smth goes wrong forward to project page
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Task createTask = new Task();
		
		createTask.setTitle(req.getParameter("title"));
		createTask.setDescription(req.getParameter("description"));
		createTask.setEmployeeId(Integer.parseInt(req.getParameter("user_id")));
		createTask.setEstimateTime(Time.valueOf(req.getParameter("estimate_time")));
		createTask.setProjectId(Integer.parseInt(req.getParameter("project_id")));
		createTask.setState("NEW");
		
		new TaskService().save(createTask);
		
		//TODO redirect to project page
		
	}

}
