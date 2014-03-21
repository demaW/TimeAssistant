package com.java.task11.webapp.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.service.UserService;
import com.java.task11.model.User;

/**
 * Servlet implementation class AddUser
 */
@WebServlet("/admin/adduser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		request.getRequestDispatcher("/pages/admin/adduser.jsp")
					.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String position = request.getParameter("position");
        String role = request.getParameter("role");
        Double salary = Double.parseDouble(request.getParameter("salary"));
        int roleId = 1;
        if (role.equals("user")) {
			roleId = 1;
		}else if (role.equals("manager")) {
			roleId = 2;
		} else if (role.equals("admin")) {
			roleId = 3;
		}
       
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setEncryptedPassword(password);
        user.setPosition(position);
        user.setRoleId(roleId);
        user.setSalaryRate(salary);
       
        UserService employeeService= new UserService();
		try {
			employeeService.save(user);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String contextPath = request.getContextPath();
		response.sendRedirect(contextPath +"/admin/users");    
	}

}
