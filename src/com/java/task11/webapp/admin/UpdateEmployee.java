package com.java.task11.webapp.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.service.UserService;
import com.java.task11.model.User;
import com.java.task11.webapp.EmailUtil;

/**
 * Servlet implementation class UpdateEmployee
 */
@WebServlet("/admin/updateemployee")
public class UpdateEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateEmployee() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("notification")==null) 
			{
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/admin/users");
			return;
			}
		int id = Integer.parseInt(request.getParameter("notification"));
		try {
			User userToEdit = new UserService().getByID(id);
			HttpSession session = request.getSession();
			
			session.setAttribute("userToEdit", userToEdit);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/pages/admin/edituser.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("update") != null) {
			updateUser(request, response);
		} else if (request.getParameter("delete") != null) {
			deleteUser(request, response);
		}
	}

	private void deleteUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		UserService employeeService = new UserService();
		
		try {
			User user = employeeService.getByID(id);
			employeeService.delete(employeeService.getByID(id));
			if(request.getParameter("mailNotification") != null && request.getParameter("mailNotification").equals("yes" ))
			{
			String email = request.getParameter("email");
			String messageText = "Your account was updated" +"\n"+ user.toString(); 
			EmailUtil emailUtil = new EmailUtil();
			emailUtil.sendMail(email, messageText);
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		String contextPath = request.getContextPath();
		response.sendRedirect(contextPath + "/admin/users");
	}

	private void updateUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String position = request.getParameter("position");
		Double salaryRate = Double.parseDouble(request.getParameter("salaryRate"));
		int roleId = 1;
		if (request.getParameter("role")!= null && !request.getParameter("role").equals("")){
		 roleId = Integer.parseInt(request.getParameter("role"));}
		
		
		UserService employeeService = new UserService();
		User user = new User();
	
		user.setId(id);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setEncryptedPassword(password);
		user.setPosition(position);
		user.setRoleId(roleId);
		user.setSalaryRate(salaryRate);
		
		try {
			employeeService.update(user);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(request.getParameter("mailNotification") != null && request.getParameter("mailNotification").equals("yes" ))
			{
			String messageText = "Your account was updated" +"\n"+ user.toString(); 
			EmailUtil emailUtil = new EmailUtil();
			emailUtil.sendMail(email, messageText);
			}
		
		String contextPath = request.getContextPath();
		response.sendRedirect(contextPath + "/admin/users");
	}

}
