package com.java.task11.model;

import java.sql.Time;
import java.util.Date;

public class Task {
	private int taskId;
	private Time estimateTime;
	private Time realTime;
	private String state;
	private String description;
	private String title;
	private Integer employeeId;
	private Integer projectId;
	private Date startDate;
	private Date endDate;
	private Date finished;

	public int getTaskId() {
		return this.taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public Time getEstimateTime() {
		return this.estimateTime;
	}

	public void setEstimateTime(Time estimateTime) {
		this.estimateTime = estimateTime;
	}

	public Time getRealTime() {
		return this.realTime;
	}

	public void setRealTime(Time realTime) {
		this.realTime = realTime;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getEmployeeId() {
		return this.employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getFinished() {
		return this.finished;
	}

	public void setFinished(Date finished) {
		this.finished = finished;
	}
}
