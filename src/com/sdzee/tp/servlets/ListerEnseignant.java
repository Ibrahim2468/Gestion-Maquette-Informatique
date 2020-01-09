package com.sdzee.tp.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.tp.beans.Enseignant;
import com.sdzee.tp.dao.DAOFactory;
import com.sdzee.tp.dao.EnseignantDao;
import com.sdzee.tp.forms.ListerEnseignantForm;

/**
 * Servlet implementation class ListerEnseignant
 */
@WebServlet("/ListerEnseignant")
public class ListerEnseignant extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	private static final String VUE = "/WEB-INF/afficherEnseignant.jsp";

	private EnseignantDao enseignantDao;

	public void init() throws ServletException {
		/* Récupération d'une instance de notre DAO Utilisateur */
		this.enseignantDao = ((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getEnseignantDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		ListerEnseignantForm form = new ListerEnseignantForm(enseignantDao);

		List<Enseignant> enseignants = form.recupererEnseignant(request);

		request.setAttribute("enseignants", enseignants);

		this.getServletContext().getRequestDispatcher(VUE)
				.forward(request, response);
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
