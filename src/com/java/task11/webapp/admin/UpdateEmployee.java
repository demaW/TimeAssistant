package com.java.task11.webapp.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.task11.controller.service.EmployeeService;
import com.java.task11.model.Employee;

/**
 * Servlet implementation class UpdateEmployee
 */
@WebServlet("/UpdateEmployee")
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
				
		EmployeeService employeeService = new EmployeeService();
		Employee employeeC = employeeService.getByID(id);
		employeeService.delete(employeeC);
		response.sendRedirect("/TimeAssistant/pages/admin/employees");
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
		int roleId = Integer.parseInt(request.getParameter("role"));
		
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee();
	
		employee.setId(id);
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setEmail(email);
		employee.setPassword(password);
		employee.setImage(imageName);
		employee.setPosition(position);
		employee.setRoleId(roleId);
	 
		employeeService.update(employee);
		
		response.sendRedirect("/TimeAssistant/pages/admin/employees");
	}

}
