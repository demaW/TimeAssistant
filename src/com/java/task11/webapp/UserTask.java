package com.java.task11.webapp;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.task11.controller.service.TaskService;
import com.java.task11.model.Task;

@WebServlet("/userTask")
public class UserTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserTask() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpSession session = request.getSession();
		//TODO get user from session, when login will be fixed
		List<Task> tasks = new TaskService().getByEmployeeId(new Integer(1));
		
		request.setAttribute("tasks", tasks);
		request.getRequestDispatcher("/pages/userTasks.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
