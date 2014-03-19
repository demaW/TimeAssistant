package com.java.task11.webapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.java.task11.controller.service.UserService;
import com.java.task11.model.User;
import com.java.task11.utils.MD5Utils;
import com.java.task11.utils.ValidationUtils;

/**
 * @author nlelyak
 * @version 1.00 2014-03-06
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("logout") != null) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            log.info("Logged out: " + user.getFirstName() + " " + user.getLastName());
//            remove current user from session
            session.removeAttribute("user");
            response.sendRedirect("/login");
        } else {
            request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = MD5Utils.getMD5String(request.getParameter("password"));
        User user = new UserService().getByEmail(email);

        if (!ValidationUtils.isNullOrEmpty(user.getEmail()) && user.getPassword().equals(password)) {
            session.setAttribute("user", user);
            log.info("Logged in: " + user.getFirstName() + " " + user.getLastName());
            if (session.getAttribute("waitUrl") != null) {
                String url = session.getAttribute("waitUrl").toString();
                response.sendRedirect(url);
            } else {
                // todo send redirect to next step
            	
            	if (user.getRoleId().equals(1)) { // 1=user role
            		String contextPath = request.getContextPath();
            		response.sendRedirect(contextPath + "/user/tasks");
            	}
            	
            }
        } else {
            request.setAttribute("loginErrors", "Wrong email or password");
            request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
        }

    }
}
