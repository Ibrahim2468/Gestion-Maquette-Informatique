package com.sdzee.tp.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.tp.beans.Classe;
import com.sdzee.tp.beans.Semestre;
import com.sdzee.tp.dao.ClasseDao;
import com.sdzee.tp.dao.DAOFactory;
import com.sdzee.tp.dao.SemestreDao;
import com.sdzee.tp.forms.CreationClasseForm;
import com.sdzee.tp.forms.CreationSemestreForm;

/**
 * Servlet implementation class ModifierSemestre
 */
@WebServlet("/ModifierSemestre")
public class ModifierSemestre extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VUE_DEFAULT = "/WEB-INF/modifierSemestre.jsp";
	private static final String VUE_SUCCES = "ListeSemestres";
	public static final String CONF_DAO_FACTORY = "daofactory";

	private SemestreDao semestreDao;
	private ClasseDao classeDao;

	int recup;

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

		recup = Integer.valueOf(request.getParameter("val")).intValue();

		CreationSemestreForm form = new CreationSemestreForm(semestreDao,
				classeDao);

		CreationClasseForm form2 = new CreationClasseForm(classeDao);

		Semestre semestre = form.trouverSemestre(recup);

		List<Classe> classes = form2.listerClasses();

		request.setAttribute("classes", classes);

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

		String nom = request.getParameter("nom");
		int classe = Integer.valueOf(request.getParameter("classe")).intValue();

		CreationSemestreForm form = new CreationSemestreForm(semestreDao,
				classeDao);

		form.modifierSemestre(recup, nom, classe);

		response.sendRedirect(VUE_SUCCES);
	}
}
