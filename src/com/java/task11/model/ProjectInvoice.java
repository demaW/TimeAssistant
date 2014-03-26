package com.java.task11.model;


public class ProjectInvoice {
private String taskName;
private String firstName;
private String lastName;
private String position;
private int workedTime;
private double salaryRate;
private double costPerEmployee;
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getPosition() {
	return position;
}
public void setPosition(String posistion) {
	this.position = posistion;
}
public int getWorkedTime() {
	return workedTime;
}
public void setWorkedTime(int workedTime) {
	this.workedTime = workedTime;
}
public double getSalaryRate() {
	return salaryRate;
}
public void setSalaryRate(double salaryRate) {
	this.salaryRate = salaryRate;
}
public double getCosPerEmployee() {
	return costPerEmployee;
}
public void setCosPerEmployee() {
		
	this.costPerEmployee =  getSalaryRate() * getWorkedTime();
}
public String getTaskName() {
	return taskName;
}
public void setTaskName(String taskName) {
	this.taskName = taskName;
}


}
