package com.sdzee.tp.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.tp.beans.Matiere;
import com.sdzee.tp.dao.ClasseDao;
import com.sdzee.tp.dao.DAOFactory;
import com.sdzee.tp.dao.MatiereDao;
import com.sdzee.tp.dao.ModuleDao;
import com.sdzee.tp.dao.SemestreDao;
import com.sdzee.tp.forms.CreationMatiereForm;

/**
 * Servlet implementation class ListeMatieres
 */
@WebServlet("/ListeMatieres")
public class ListeMatieres extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VUE_DEFAULT = "/WEB-INF/afficherMatieres.jsp";
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

		List<Matiere> matieres = form.listerMatieres();

		// System.out.println("Les matieres : ");
		// for (int i = 0; i < matieres.size(); i++) {
		// System.out.println(matieres.get(i).getIntitule_matiere());
		// }

		request.setAttribute("matieres", matieres);

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
