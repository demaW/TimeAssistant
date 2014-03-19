package com.java.task11.webapp.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.task11.controller.service.UserService;
import com.java.task11.model.User;

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
		// TODO Auto-generated method stub
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
		int id = Integer.parseInt(request.getParameter("delete"));
				
		UserService employeeService = new UserService();
		User userC = employeeService.getByID(id);
		employeeService.delete(userC);
		String contextPath = request.getContextPath();
		response.sendRedirect(contextPath + "/admin/users");
	}

	private void updateUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("update"));

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String imageName = "default.png";
		String position = request.getParameter("position");
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
		user.setImage(imageName);
		user.setPosition(position);
		user.setRoleId(roleId);
	 
		employeeService.update(user);
		String contextPath = request.getContextPath();
		response.sendRedirect(contextPath + "/admin/users");
	}

}
