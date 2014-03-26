package com.java.task11.webapp.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.task11.controller.dao.factory.DAOException;
import com.java.task11.controller.service.ProjectInvoiceService;
import com.java.task11.controller.service.ProjectService;
import com.java.task11.model.Project;
import com.java.task11.model.ProjectInvoice;

/**
 * Servlet implementation class InvoiceProceed
 */
@WebServlet("/manager/invoice")
public class InvoiceProceed extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InvoiceProceed() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// int projectId = Integer.parseInt(request.getParameter("projectId"));
		int projectID = 1;
		Double sumCost = new Double(0);
		Double planedSumCost = new Double(0);
		Project project = null;
		try {
			project = new ProjectService().getByID(projectID);
		} catch (DAOException e) {
			e.printStackTrace();
		}

		List<ProjectInvoice> invoices = new ProjectInvoiceService()
				.getInvoice(projectID);
		for (ProjectInvoice projectInvoice : invoices) {
			
			sumCost+= projectInvoice.getCosPerEmployee();
			planedSumCost+=projectInvoice.getPlanedCostPerEmployee();
		}
		request.setAttribute("planedSumCost", planedSumCost);
		request.setAttribute("sumCost", sumCost);
		request.setAttribute("project", project);
		request.setAttribute("invoices", invoices);

		request.getRequestDispatcher("/pages/manager/invoice.jsp").forward(
				request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
