package com.java.task11.webapp.manager;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.service.TaskService;
import com.java.task11.model.Task;
import com.java.task11.utils.ValidationUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author nlelyak
 * @version 1.00 2014-03-21
 */
@WebServlet("/manager/taskstable")
@MultipartConfig
public class TasksTableServlet extends HttpServlet {
    private static Logger log = Logger.getLogger(AddTaskServlet.class);
    private TaskService taskService;
    private List<Task> tasks;
    private static final String DATE_FORMAT = "MM/dd/yyyy";

    public static final String ATTRIBUTE_TO_MODEL = "tasksList";
    public static final String PAGE_OK = "/pages/manager/tasksTable.jsp";
    public static final String PAGE_ERROR_URL = "error";

    @Override
    public void init() throws ServletException {
        taskService = new TaskService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (request.getParameter("project_id") != null) {
                updateTable(request);
                if (!tasks.isEmpty()) {
                    request.setAttribute(ATTRIBUTE_TO_MODEL, tasks);
                    request.getRequestDispatcher(PAGE_OK);
                    return;
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        response.sendRedirect(PAGE_ERROR_URL);
    }

    private void updateTable(HttpServletRequest request) {
        try {
            int id = Integer.parseInt(request.getParameter("project_id"));
            tasks = taskService.getByProjectId(id);
        } catch (DAOException e) {
            log.error(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        if (request.getParameter("delete") != null) {
            deleteTask(request);
        } else if (request.getParameter("update") != null) {
            updateTask(request);
        }
        doGet(request, response);
    }

    private void updateTask(HttpServletRequest request) {
        try {
            int id = Integer.parseInt(request.getParameter("update"));
            Task task = taskService.getByID(id);

            String name = (!ValidationUtils.isNullOrEmpty(request.getParameter("name-" + id)))
                    ? request.getParameter("task_name-" + id) : task.getTitle();
            String description = (!ValidationUtils.isNullOrEmpty(request.getParameter("task_description-" + id)))
                    ? request.getParameter("task_description-" + id) : task.getDescription();
            String state = request.getParameter("state-" + id);
            int estimateTime = (request.getParameter("estimate_time-" + id) != null)
                    ? Integer.parseInt(request.getParameter("estimate_time-" + id))
                    : task.getEstimateTime();
            Date startDate = (request.getParameter("start_date-" + id) != null)
                    ? new SimpleDateFormat(DATE_FORMAT).parse(request.getParameter("start_date-" + id))
                    : task.getStartDate();
            Date endDate = (request.getParameter("end_date-" + id) != null)
                    ? new SimpleDateFormat(DATE_FORMAT).parse(request.getParameter("end_date-" + id))
                    : task.getStartDate();

            task.setTitle(name);
            task.setDescription(description);
            task.setState(state);
            task.setStartDate(startDate);
            task.setEndDate(endDate);
            task.setEstimateTime(estimateTime);

            taskService.update(task);
        } catch (DAOException | ParseException e) {
            log.error(e);
        }
    }

    private void deleteTask(HttpServletRequest request) {
        String[] tasks = request.getParameterValues("checkedTasks");
        for (String taskId : tasks) {
            taskService.delete(Integer.parseInt(taskId), this);
        }
    }

}
