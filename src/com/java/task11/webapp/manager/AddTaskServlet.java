package com.java.task11.webapp.manager;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.service.ProjectService;
import com.java.task11.controller.service.TaskService;
import com.java.task11.controller.service.UserService;
import com.java.task11.model.Project;
import com.java.task11.model.Task;
import com.java.task11.model.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/manager/addTask")
public class AddTaskServlet extends HttpServlet {
    private static Logger log = Logger.getLogger(AddTaskServlet.class);
    private UserService userService;
    private ProjectService projectService;
    private List<User> usersList;
    private List<Project> projectList;
    private Integer projectId;

    public static final String PAGE_ADD_TASK = "/pages/manager/addTask.jsp";
    public static final String PAGE_SEE_TASKS = "/pages/manager/tasksTable.jsp";

    @Override
    public void init() throws ServletException {
        try {
            userService = new UserService();
            projectService = new ProjectService();
            projectList = projectService.getListOfObjects();
        } catch (DAOException e) {
            log.error(e);
        }
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            if (request.getParameter("project_id") != null) {
                projectId = Integer.parseInt(request.getParameter("project_id"));
                usersList = userService.getUsersByProjectId(projectId);

                request.setAttribute("project_id", projectId);
            } else {
                usersList = userService.getListOfObjects();
            }

            if (!projectList.isEmpty()) {
                request.setAttribute("projectList", projectList);
            }

            request.setAttribute("users_in_project", usersList);
            request.getRequestDispatcher(PAGE_ADD_TASK).forward(request, response);
        } catch (NumberFormatException | DAOException e) {
			log.error(e);
            request.getRequestDispatcher(PAGE_SEE_TASKS).forward(request, response);
        }

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Task createTask = new Task();
		
		createTask.setTitle(request.getParameter("title"));
		createTask.setDescription(request.getParameter("description"));
		createTask.setEmployeeId(Integer.parseInt(request.getParameter("user_id")));
		createTask.setEstimateTime(Integer.parseInt(request.getParameter("estimate_time")));
		createTask.setProjectId(Integer.parseInt(request.getParameter("project_id")));
		createTask.setState("NEW");
		
		try {
			new TaskService().save(createTask);
		} catch (DAOException e) {
            log.error(e);
		}
		
		//redirect to projects page
        if (projectId != null) {
            response.sendRedirect("/manager/taskstable?project_id=" + projectId);
            return;
        }
//        request.getRequestDispatcher("/pages/manager/projectsTable.jsp").forward(request, response);
        response.sendRedirect(request.getContextPath() + "/manager/projectstable");
    }

}
