package com.sdzee.tp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.tp.beans.Classe;
import com.sdzee.tp.dao.ClasseDao;
import com.sdzee.tp.dao.DAOFactory;
import com.sdzee.tp.forms.CreationClasseForm;

/**
 * Servlet implementation class AfficherClasse
 */
@WebServlet("/ListeClasse")
public class ListeClasse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE_DEFAULT = "/WEB-INF/afficherClasse.jsp";
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

		Classe classe = form.trouverClasse(recup);

		System.out.println(classe.getId_classe());
		System.out.println(classe.getNom_classe());
		System.out.println(classe.getIdentifiant_classe());
		System.out.println(classe.getPasse_classe());
		System.out.println(classe.getEmail_classe());

		request.setAttribute("classe", classe);

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
	}

}
