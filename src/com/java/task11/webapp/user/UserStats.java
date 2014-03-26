package com.java.task11.webapp.user;

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
import com.java.task11.model.Task;
import com.java.task11.model.User;
import com.java.task11.utils.TimeUtils;

@WebServlet("/user/stats")
public class UserStats extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserStats() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			Integer userId = user.getId();

			List<Task> finishedTasks = new TaskService()
					.getByEmployeeIdAndStatus(userId, "FINISHED");

			Time summaryRealTime;
			for (Task task : finishedTasks) {
				summaryRealTime = TimeUtils.addTimes(task.getRealTime(), task.getEstimateTime());
				System.out.println(summaryRealTime);
			}
			
			request.getRequestDispatcher("/pages/user/userStats.jsp").forward(
					request, response);

		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
