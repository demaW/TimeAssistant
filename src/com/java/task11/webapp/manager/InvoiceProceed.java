package com.java.task11.webapp.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.service.TaskService;
import com.java.task11.controller.service.UserService;
import com.java.task11.model.Task;
import com.java.task11.model.User;

/**
 * Servlet implementation class InvoiceProceed
 */
@WebServlet("/InvoiceProceed")
public class InvoiceProceed extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvoiceProceed() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//int projectId = Integer.parseInt(request.getParameter("projectId"));	
		int projectId = 1;
		try {
			List<Task> tasks = new TaskService().getByProjectId(projectId);
			List<User> users = new ArrayList<>();
			for (Task task : tasks) {
				users.add(new UserService().getByID(task.getEmployeeId()));
			}
			List<Object> invoice = new ArrayList<>();
			invoice.addAll(tasks);
			invoice.addAll(users);
			request.setAttribute("invoice", invoice);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/pages/manager/invoice.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
