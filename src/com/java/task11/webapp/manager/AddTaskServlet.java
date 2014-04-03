package com.java.task11.webapp.manager;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.service.ProjectService;
import com.java.task11.controller.service.TaskService;
import com.java.task11.controller.service.UserService;
import com.java.task11.model.Project;
import com.java.task11.model.Task;
import com.java.task11.model.User;
import com.java.task11.utils.EmailUtil;

@WebServlet("/manager/addTask")
public class AddTaskServlet extends HttpServlet {
    private static Logger log = Logger.getLogger(AddTaskServlet.class);
    private UserService userService;
    private List<User> usersList;
    private List<Project> projectList;
    private Integer projectId;
    private static final String DATE_FORMAT = "MM/dd/yy";

    public static final String PAGE_ADD_TASK = "/pages/manager/addTask.jsp";
    public static final String PAGE_SEE_TASKS = "/pages/manager/tasksTable.jsp";

    @Override
    public void init() throws ServletException {
        try {
            userService = new UserService();
            ProjectService projectService = new ProjectService();
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
        try {
            createTask.setTitle(request.getParameter("title"));
            createTask.setDescription(request.getParameter("description"));
            createTask.setEmployeeId(Integer.parseInt(request.getParameter("user_id")));
            createTask.setEstimateTime(Integer.parseInt(request.getParameter("estimate_time")));
            createTask.setProjectId(Integer.parseInt(request.getParameter("project_id")));
            createTask.setStartDate(new SimpleDateFormat(DATE_FORMAT).parse(request.getParameter("startDate")));
            createTask.setEndDate(new SimpleDateFormat(DATE_FORMAT).parse(request.getParameter("endDate")));
            createTask.setState("NEW");
            createTask.setRealTime(0);

			new TaskService().save(createTask);
		} catch (Exception e) {
            log.error(e);
		}
        if (request.getParameter("mailNotification") != null
				&& request.getParameter("mailNotification").equals("yes")){
        	int userId = Integer.parseInt(request.getParameter("user_id"));
        	sendTastkMailNotification(userId,createTask);
       
        }
	
		String contextPath = request.getContextPath();
		//redirect to projects page
        if (projectId != null) {
            response.sendRedirect(contextPath+"/manager/taskstable?project_id=" + projectId);
            return;
        }
//        request.getRequestDispatcher("/pages/manager/projectsTable.jsp").forward(request, response);
        response.sendRedirect(contextPath+"/manager/projectstable");
    }
	
	private void sendTastkMailNotification(int userId,Task task){
		 {
			User user=null;
			try {
				user = new  UserService().getByID(userId);
			} catch (DAOException e) {
				e.printStackTrace();
			}
			String subject = "New task assigned";
			String messageText = "Your have new task" + "\n"
					+"Project name"+ task.getProject()+ "\n"
					+ "Task name:" + task.getTitle()+"\n"
					+"Description:" +task.getDescription();
			EmailUtil emailUtil = new EmailUtil();
			emailUtil.sendMail(user.getEmail(),subject, messageText);
		}
	}

}
