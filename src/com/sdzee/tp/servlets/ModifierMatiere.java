package com.sdzee.tp.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.tp.beans.Matiere;
import com.sdzee.tp.beans.Module;
import com.sdzee.tp.dao.ClasseDao;
import com.sdzee.tp.dao.DAOFactory;
import com.sdzee.tp.dao.MatiereDao;
import com.sdzee.tp.dao.ModuleDao;
import com.sdzee.tp.dao.SemestreDao;
import com.sdzee.tp.forms.CreationMatiereForm;
import com.sdzee.tp.forms.CreationModuleForm;

/**
 * Servlet implementation class ModifierMatiere
 */
@WebServlet("/ModifierMatiere")
public class ModifierMatiere extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VUE_DEFAULT = "/WEB-INF/modifierMatiere.jsp";
	private static final String VUE_SUCCES = "ListeMatieres";
	public static final String CONF_DAO_FACTORY = "daofactory";

	private MatiereDao matiereDao;
	private ModuleDao moduleDao;
	private SemestreDao semestreDao;
	private ClasseDao classeDao;

	int recup;

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

		recup = Integer.valueOf(request.getParameter("val")).intValue();

		CreationMatiereForm form = new CreationMatiereForm(matiereDao,
				moduleDao, semestreDao, classeDao);

		CreationModuleForm form2 = new CreationModuleForm(moduleDao,
				semestreDao, classeDao);

		Matiere matiere = form.trouverMatiere(recup);

		List<Module> modules = form2.listerModules();

		request.setAttribute("matiere", matiere);

		request.setAttribute("modules", modules);

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

		String intitule = request.getParameter("intitule");
		int volume = Integer.valueOf(request.getParameter("volume")).intValue();
		int module = Integer.valueOf(request.getParameter("module")).intValue();

		CreationMatiereForm form = new CreationMatiereForm(matiereDao,
				moduleDao, semestreDao, classeDao);

		form.modifierMatiere(recup, intitule, volume, module);

		response.sendRedirect(VUE_SUCCES);
	}
}
