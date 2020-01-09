package com.sdzee.tp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.tp.beans.Enseignant;
import com.sdzee.tp.dao.DAOFactory;
import com.sdzee.tp.dao.EnseignantDao;
import com.sdzee.tp.forms.ModifieerEnseignantForm;

/**
 * Servlet implementation class modifierEnseignant
 */
@WebServlet("/ModifierEnseignant")
public class ModifierEnseignant extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE = "/WEB-INF/modifierEnseignant.jsp";
	public static final String CONF_DAO_FACTORY = "daofactory";

	int valeur;

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

		valeur = Integer.valueOf(request.getParameter("val")).intValue();

		Enseignant enseignant = enseignantDao.trouver(Integer.valueOf(
				request.getParameter("val")).intValue());

		request.setAttribute("enseignant", enseignant);

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

		System.out.println("La valeur est : " + valeur);
		ModifieerEnseignantForm form = new ModifieerEnseignantForm(
				enseignantDao, valeur);

		Enseignant enseignant = form.creerEnseignant(request, valeur);

	}
}
