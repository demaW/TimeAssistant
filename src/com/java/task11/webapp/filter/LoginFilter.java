package com.java.task11.webapp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.task11.model.User;

@WebFilter({ "/user/*", "/admin/*", "/manager/*" })
public class LoginFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();

		if (session == null || session.getAttribute("user") == null) {
			httpRequest.setAttribute("loginErrors", "Login please");
			httpRequest.getRequestDispatcher("/pages/login.jsp").forward(
					httpRequest, httpResponse);
		} else {
			User user = (User) session.getAttribute("user");
			if (httpRequest.getRequestURI().contains("/admin/")) {
				if (user.getRoleId().equals(3)) {
					chain.doFilter(request, response);
				} else {
					httpRequest
							.setAttribute("loginErrors", "You are not admin");
					httpRequest.getRequestDispatcher("/pages/login.jsp")
							.forward(httpRequest, httpResponse);
				}
			} else if (httpRequest.getRequestURI().contains("/manager/")) {
				if (user.getRoleId().equals(2)) {
					chain.doFilter(request, response);
				} else {
					httpRequest.setAttribute("loginErrors",
							"You are not manager");
					httpRequest.getRequestDispatcher("/pages/login.jsp")
							.forward(httpRequest, httpResponse);
				}
			} else if (httpRequest.getRequestURI().contains("/user/")) {
				if (user.getRoleId().equals(1)) {
					chain.doFilter(request, response);
				} else {
					httpRequest.setAttribute("loginErrors", "You are not user");
					httpRequest.getRequestDispatcher("/pages/login.jsp")
							.forward(httpRequest, httpResponse);
				}
			} else {
				chain.doFilter(request, response);
			}
		}
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
