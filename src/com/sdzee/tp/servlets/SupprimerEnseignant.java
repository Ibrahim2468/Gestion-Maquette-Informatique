package com.sdzee.tp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.tp.dao.DAOFactory;
import com.sdzee.tp.dao.EnseignantDao;

/**
 * Servlet implementation class SupprimerEnseignant
 */
@WebServlet("/SupprimerEnseignant")
public class SupprimerEnseignant extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String CONF_DAO_FACTORY = "daofactory";
	private static final String VUE_SUCCES = "ListerEnseignant";

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

		enseignantDao.supprimer_par_id(Integer.valueOf(
				request.getParameter("val")).intValue());

		response.sendRedirect(VUE_SUCCES);
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
