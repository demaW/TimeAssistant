package com.java.task11.webapp.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.service.UserService;
import com.java.task11.model.User;
import com.java.task11.utils.ValidationErrors;
import com.java.task11.utils.ValidationUtils;

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
        String salaryS = request.getParameter("salary");
        Double salary = Double.parseDouble(salaryS);
        int roleId = 1;
        if (role.equals("user")) {
			roleId = 1;
		}else if (role.equals("manager")) {
			roleId = 2;
		} else if (role.equals("admin")) {
			roleId = 3;
		}
        List<String> addingErrors = validateInputs(firstName, lastName, email, password,salaryS);

        if (addingErrors.size() > 0) {
            request.setAttribute("registrationErrors", addingErrors);
            request.getRequestDispatcher("/pages/admin/adduser.jsp").forward(request, response);
        } else {
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
		response.sendRedirect(contextPath +"/admin/users");    }
	}
	
	 private List<String> validateInputs(String firstName, String lastName, String email, String password, String salary) {
	        List<String> registrationErrors = new ArrayList<>();

	        if (ValidationUtils.isNullOrEmpty(firstName)) {
	            registrationErrors.add(ValidationErrors.FIRST_NAME);
	        }
	        if (ValidationUtils.isNullOrEmpty(lastName)) {
	            registrationErrors.add(ValidationErrors.LAST_NAME);
	        }
	        if (!ValidationUtils.validEmail(email)) {
	            registrationErrors.add(ValidationErrors.EMAIL);
	        }
	        	       
	        if (ValidationUtils.isNullOrEmpty(password)) {
	            registrationErrors.add(ValidationErrors.PASSWORD);
	        }
	        if (ValidationUtils.isNullOrEmpty(salary)) {
				registrationErrors.add(ValidationErrors.NOTEMPTY);
			}
	        if (ValidationUtils.isNumber(salary)) {
				registrationErrors.add(ValidationErrors.SALARY);
			}
	        return registrationErrors;    }

}
