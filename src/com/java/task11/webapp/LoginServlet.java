package com.java.task11.webapp;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.service.UserService;
import com.java.task11.model.User;
import com.java.task11.utils.MD5Utils;
import com.java.task11.utils.ValidationUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author nlelyak
 * @version 1.00 2014-03-06
 */
@WebServlet( urlPatterns = {"/login","/admin/login", "/user/login", "/manager/login"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = MD5Utils.getMD5String(request.getParameter("password"));
        User user = null;
		try {
			user = new UserService().getByEmail(email);
		} catch (DAOException e) {
			log.error(e);
		}

        if (user != null) {
            if (!ValidationUtils.isNullOrEmpty(user.getEmail()) && user.getPassword().equals(password)) {
                session.setAttribute("user", user);
                log.info("Logged in: " + user.getFirstName() + " " + user.getLastName());
                if (session.getAttribute("waitUrl") != null) {
                    String url = session.getAttribute("waitUrl").toString();
                    response.sendRedirect(url);
                } else {
                    String contextPath = request.getContextPath();

                    if (user.getRoleId().equals(1)) { // 1=user role
                        response.sendRedirect(contextPath + "/user/tasks");
                    } if (user.getRoleId().equals(2)) { // 2 manager role
                        response.sendRedirect(contextPath + "/manager/projects");
                    } if (user.getRoleId().equals(3)) { // 3 admin role
                        response.sendRedirect(contextPath + "/admin/users");
                    }

                }
            } else {
                request.setAttribute("loginErrors", "Wrong email or password");
                request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
            }
        }

    }
}
