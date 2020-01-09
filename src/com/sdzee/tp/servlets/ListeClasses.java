package com.sdzee.tp.servlets;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class ListeClasses
 */
@WebServlet("/ListeClasses")
public class ListeClasses extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE_DEFAULT = "/WEB-INF/afficherClasses.jsp";
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

		List<Classe> classes = form.listerClasses();

		request.setAttribute("classes", classes);

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
