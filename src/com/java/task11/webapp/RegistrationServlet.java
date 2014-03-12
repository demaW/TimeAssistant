package com.java.task11.webapp;

import com.java.task11.controller.service.EmployeeService;
import com.java.task11.model.Employee;
import com.java.task11.utils.FileUploadUtils;
import com.java.task11.utils.ValidationErrors;
import com.java.task11.utils.ValidationUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nlelyak
 * @version 1.00 2014-03-06
 */
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private static Logger log = Logger.getLogger(RegistrationServlet.class);
    private EmployeeService employeeService = new EmployeeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        processRegistration(request, response);
    }

    private void processRegistration(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        
        String email = request.getParameter("email");
        System.out.println(email);
        String password = request.getParameter("password");
        String imageName = "default.png";
        String position = request.getParameter("position");

        List<String> registrationErrors = validateInputs(firstName, lastName, email, password);

        if (registrationErrors.size() > 0) {
            request.setAttribute("registrationErrors", registrationErrors);
            request.getRequestDispatcher("/pages/registration.jsp").forward(request, response);
        } else {
            Employee employee = new Employee();
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setEmail(email);
            employee.setEncryptedPassword(password);
            employee.setImage(imageName);
            employee.setPosition(position);

            employeeService.save(employee);
            
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/login");
        }

    }

    private List<String> validateInputs(String firstName, String lastName, String email, String password) {
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
        
        if (employeeService.getByEmail(email) != null) {
            registrationErrors.add(ValidationErrors.EMAIL_ALREADY_PRESENT);
        }
        if (ValidationUtils.isNullOrEmpty(password)) {
            registrationErrors.add(ValidationErrors.PASSWORD);
        }
        return registrationErrors;    }
}
