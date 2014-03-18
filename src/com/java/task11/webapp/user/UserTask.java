package com.java.task11.webapp.user;

import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.task11.controller.service.TaskService;
import com.java.task11.model.Task;

@WebServlet("/user/tasks")
public class UserTask extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserTask() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// HttpSession session = request.getSession();
		// TODO get user from session, when login will be fixed
		List<Task> tasks = new TaskService().getByEmployeeId(new Integer(1));

		String filterStatusValue = request.getParameter("status");
		if (filterStatusValue != null) {
			tasks = filterResults(tasks, filterStatusValue);
		}

		request.setAttribute("tasks", tasks);
		request.getRequestDispatcher("/pages/user/userTasks.jsp").forward(request,
				response);

	}

	protected List<Task> filterResults(List<Task> tasks, String status) {

			for (ListIterator<Task> iterator = tasks.listIterator(); iterator
					.hasNext();) {
				if (!iterator.next().getState().equalsIgnoreCase(status)) {
					iterator.remove();
				}
			}

		return tasks;
	}

}
