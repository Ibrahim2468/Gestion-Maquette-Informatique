package com.sdzee.tp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.tp.dao.DAOFactory;
import com.sdzee.tp.dao.SalleDao;
import com.sdzee.tp.forms.CreationSalleForm;

/**
 * Servlet implementation class SupprimerSalle
 */
@WebServlet("/SupprimerSalle")
public class SupprimerSalle extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VUE_DEFAULT = "ListeSalles";
	public static final String CONF_DAO_FACTORY = "daofactory";

	private SalleDao salleDao;

	public void init() throws ServletException {
		/* Récupération d'une instance de notre DAO Utilisateur */
		this.salleDao = ((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getSalleDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		CreationSalleForm form = new CreationSalleForm(salleDao);

		int recup = Integer.valueOf(request.getParameter("val")).intValue();

		form.supprimerSalle(recup);

		response.sendRedirect(VUE_DEFAULT);
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
