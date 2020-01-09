package com.sdzee.tp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.tp.beans.Semestre;
import com.sdzee.tp.dao.ClasseDao;
import com.sdzee.tp.dao.DAOFactory;
import com.sdzee.tp.dao.SemestreDao;
import com.sdzee.tp.forms.CreationSemestreForm;

/**
 * Servlet implementation class ListeSemestre
 */
@WebServlet("/ListeSemestre")
public class ListeSemestre extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE_DEFAULT = "/WEB-INF/afficherSemestre.jsp";
	public static final String CONF_DAO_FACTORY = "daofactory";

	private SemestreDao semestreDao;
	private ClasseDao classeDao;

	public void init() throws ServletException {
		/* Récupération d'une instance de notre DAO Utilisateur */
		this.semestreDao = ((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getSemestreDao();
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

		CreationSemestreForm form = new CreationSemestreForm(semestreDao,
				classeDao);

		int recup = Integer.valueOf(request.getParameter("val")).intValue();

		Semestre semestre = form.trouverSemestre(recup);

		System.out.println("Le recup : ");
		System.out.println(semestre.getClasse().getNom_classe());

		request.setAttribute("semestre", semestre);

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
