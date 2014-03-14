package com.java.task11.controller.dao2;

import com.java.task11.model.Task;

/**
 * @author nlelyak
 * @version 1.00 2014-03-05
 */
public class TaskDao extends ElementDAOImpl<Task> {
    public TaskDao() {
        super(Task.class);
    }
}
