package com.sdzee.tp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.tp.beans.Salle;
import com.sdzee.tp.dao.DAOFactory;
import com.sdzee.tp.dao.SalleDao;
import com.sdzee.tp.forms.CreationSalleForm;

/**
 * Servlet implementation class ModifierSalle
 */
@WebServlet("/ModifierSalle")
public class ModifierSalle extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VUE_DEFAULT = "/WEB-INF/modifierSalle.jsp";
	private static final String VUE_SUCCES = "ListeSalles";
	public static final String CONF_DAO_FACTORY = "daofactory";

	private SalleDao salleDao;

	int recup;

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

		recup = Integer.valueOf(request.getParameter("val")).intValue();

		CreationSalleForm form = new CreationSalleForm(salleDao);

		Salle salle = form.trouverSalle(recup);

		request.setAttribute("salle", salle);

		this.getServletContext().getRequestDispatcher(VUE_DEFAULT)
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String nom = request.getParameter("nomSalle");

		CreationSalleForm form = new CreationSalleForm(salleDao);

		form.modifierSalle(recup, nom);

		response.sendRedirect(VUE_SUCCES);
	}

}
