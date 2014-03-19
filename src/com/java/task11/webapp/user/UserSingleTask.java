package com.java.task11.webapp.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.task11.controller.service.TaskService;
import com.java.task11.model.Task;

@WebServlet("/user/task")
public class UserSingleTask extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserSingleTask() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Integer taskId = Integer.parseInt(request.getParameter("task_id"));
			
			Task task = new TaskService().getByID(taskId);
			
			request.setAttribute("task", task);
			request.getRequestDispatcher("/pages/user/userSingleTask.jsp").forward(request, response);
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			response.sendRedirect("user/tasks");
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(request.getAttribute("task"));
		
	}

}
