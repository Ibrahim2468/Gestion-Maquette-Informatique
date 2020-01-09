package com.sdzee.tp.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.tp.beans.Cour;
import com.sdzee.tp.dao.ClasseDao;
import com.sdzee.tp.dao.CourDao;
import com.sdzee.tp.dao.DAOFactory;
import com.sdzee.tp.dao.EnseignantDao;
import com.sdzee.tp.dao.MatiereDao;
import com.sdzee.tp.dao.ModuleDao;
import com.sdzee.tp.dao.SalleDao;
import com.sdzee.tp.dao.SemestreDao;

/**
 * Servlet implementation class Enseignant
 */
@WebServlet("/Enseignant")
public class Enseignant extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VUE_DEFAULT = "/WEB-INF/enseignant.jsp";
	public static final String CONF_DAO_FACTORY = "daofactory";

	private CourDao courDao;
	private EnseignantDao enseignantDao;
	private SalleDao salleDao;
	private MatiereDao matiereDao;
	private ModuleDao moduleDao;
	private SemestreDao semestreDao;
	private ClasseDao classeDao;

	int recup;

	public void init() throws ServletException {
		/* Récupération d'une instance de notre DAO Utilisateur */

		this.courDao = ((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getCourDao();
		this.enseignantDao = ((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getEnseignantDao();
		this.salleDao = ((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getSalleDao();
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

		recup = Integer.valueOf(request.getParameter("val")).intValue();
		request.setAttribute("val", recup);

		List<Cour> cours = courDao.listerCourenseignant(enseignantDao,
				matiereDao, salleDao, moduleDao, semestreDao, classeDao, recup);

		request.setAttribute("cours", cours);

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
