package com.java.task11.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author nlelyak
 * @version 1.00 2014-03-05
 */
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", length = 50)
    private String taskTitle;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "employee_id")
    private Employee assigned;

    @Column(name = "description", length = 60)
    private String taskDescription;

    @Column(name = "estimate_time")
    private Date estimateTime;

    @Column(name = "real_time")
    private Date realTime;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "subtask")
    private Subtask subtask;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private TaskState state;

    private enum TaskState {
        NEW, IN_PROGRESS, DONE
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Employee getAssigned() {
        return assigned;
    }

    public void setAssigned(Employee assigned) {
        this.assigned = assigned;
    }

    public Date getEstimateTime() {
        return estimateTime;
    }

    public void setEstimateTime(Date estimateTime) {
        this.estimateTime = estimateTime;
    }

    public Date getRealTime() {
        return realTime;
    }

    public void setRealTime(Date realTime) {
        this.realTime = realTime;
    }

    public Subtask getSubtasks() {
        return subtask;
    }

    public void setSubtasks(Subtask subtask) {
        this.subtask = subtask;
    }

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }
}
