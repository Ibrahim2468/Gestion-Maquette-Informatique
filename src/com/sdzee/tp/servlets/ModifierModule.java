package com.sdzee.tp.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.tp.beans.Module;
import com.sdzee.tp.beans.Semestre;
import com.sdzee.tp.dao.ClasseDao;
import com.sdzee.tp.dao.DAOFactory;
import com.sdzee.tp.dao.ModuleDao;
import com.sdzee.tp.dao.SemestreDao;
import com.sdzee.tp.forms.CreationModuleForm;
import com.sdzee.tp.forms.CreationSemestreForm;

/**
 * Servlet implementation class ModifierModule
 */
@WebServlet("/ModifierModule")
public class ModifierModule extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VUE_DEFAULT = "/WEB-INF/modifierModule.jsp";
	private static final String VUE_SUCCES = "ListeModules";
	public static final String CONF_DAO_FACTORY = "daofactory";

	private ModuleDao moduleDao;
	private SemestreDao semestreDao;
	private ClasseDao classeDao;

	int recup;

	public void init() throws ServletException {
		/* Récupération d'une instance de notre DAO Utilisateur */
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

		CreationModuleForm form = new CreationModuleForm(moduleDao,
				semestreDao, classeDao);

		CreationSemestreForm form2 = new CreationSemestreForm(semestreDao,
				classeDao);

		Module module = form.trouverModule(recup);

		List<Semestre> semestres = form2.listerSemestres();

		request.setAttribute("semestres", semestres);

		request.setAttribute("module", module);

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
		int semestre = Integer.valueOf(request.getParameter("semestre"))
				.intValue();

		CreationModuleForm form = new CreationModuleForm(moduleDao,
				semestreDao, classeDao);

		form.modifierModule(recup, nom, semestre);

		response.sendRedirect(VUE_SUCCES);
	}

}
