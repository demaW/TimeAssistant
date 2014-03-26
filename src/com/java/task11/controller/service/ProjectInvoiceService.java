package com.java.task11.controller.service;

import java.util.List;

import com.java.task11.controller.dao.implement.ProjectIvoiceDao;
import com.java.task11.model.ProjectInvoice;


public class ProjectInvoiceService {

	public List<ProjectInvoice> getInvoice(int projectId){
		return new ProjectIvoiceDao().getByPojectId(projectId);
	}
}
