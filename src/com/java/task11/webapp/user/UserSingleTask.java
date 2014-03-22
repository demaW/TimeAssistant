package com.java.task11.webapp.user;

import java.io.IOException;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.task11.controller.dao.factory.DAOException;
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
			request.getRequestDispatcher("/pages/user/userSingleTask.jsp")
					.forward(request, response);

		} catch (NumberFormatException | DAOException e) {
			e.printStackTrace();
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/user/tasks");
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			TaskService taskService = new TaskService();

			Integer taskId = Integer.parseInt(request.getParameter("task_id"));
			String timeString = request.getParameter("realTime");
			Time time = Time.valueOf(timeString);
			String isFinished = request.getParameter("finished");

			Task task = taskService.getByID(taskId);
			task.setRealTime(time);
			
			if (isFinished == null) {
				task.setState("IN Progress");		
			} else {
				task.setState("FINISHED");
			}

			taskService.update(task);

			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/user/tasks");

		} catch (NumberFormatException | DAOException e) {
			e.printStackTrace();
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/user/tasks");
		}
	}

}
