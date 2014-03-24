package com.java.task11.model;

import java.sql.Time;
import java.util.Date;

public class Hour {
	private int hoursId;
	private int userId;
	private Date date;
	private Time hours;

	public int getHoursId() {
		return hoursId;
	}

	public void setHoursId(int hoursId) {
		this.hoursId = hoursId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getHours() {
		return hours;
	}

	public void setHours(Time hours) {
		this.hours = hours;
	}
}
