package com.sdzee.tp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.tp.dao.ClasseDao;
import com.sdzee.tp.dao.DAOFactory;
import com.sdzee.tp.dao.MatiereDao;
import com.sdzee.tp.dao.ModuleDao;
import com.sdzee.tp.dao.SemestreDao;
import com.sdzee.tp.forms.CreationMatiereForm;

/**
 * Servlet implementation class SupprimerMatiere
 */
@WebServlet("/SupprimerMatiere")
public class SupprimerMatiere extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VUE_DEFAULT = "ListeMatieres";
	public static final String CONF_DAO_FACTORY = "daofactory";

	private MatiereDao matiereDao;
	private ModuleDao moduleDao;
	private SemestreDao semestreDao;
	private ClasseDao classeDao;

	public void init() throws ServletException {
		/* Récupération d'une instance de notre DAO Utilisateur */
		this.matiereDao = ((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getMatiereDao();
		this.moduleDao = ((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getModuleDao();
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

		CreationMatiereForm form = new CreationMatiereForm(matiereDao,
				moduleDao, semestreDao, classeDao);

		int recup = Integer.valueOf(request.getParameter("val")).intValue();

		form.supprimerMatiere(recup);

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
