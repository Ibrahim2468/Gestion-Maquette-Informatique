package com.sdzee.tp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.tp.dao.ClasseDao;
import com.sdzee.tp.dao.DAOFactory;
import com.sdzee.tp.forms.CreationClasseForm;

/**
 * Servlet implementation class SupprimerClasse
 */
@WebServlet("/SupprimerClasse")
public class SupprimerClasse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE_DEFAULT = "ListeClasses";
	public static final String CONF_DAO_FACTORY = "daofactory";

	private ClasseDao classeDao;

	public void init() throws ServletException {
		/* Récupération d'une instance de notre DAO Utilisateur */
		this.classeDao = ((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getClasseDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		CreationClasseForm form = new CreationClasseForm(classeDao);

		int recup = Integer.valueOf(request.getParameter("val")).intValue();

		form.supprimerClasse(recup);

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
