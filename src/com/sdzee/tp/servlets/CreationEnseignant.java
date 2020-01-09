package com.sdzee.tp.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdzee.tp.beans.Enseignant;
import com.sdzee.tp.dao.DAOFactory;
import com.sdzee.tp.dao.EnseignantDao;
import com.sdzee.tp.forms.CreationEnseignantForm;

/**
 * Servlet implementation class CreationEnseignant
 */
@WebServlet("/CreationEnseignant")
public class CreationEnseignant extends HttpServlet {

	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_ENSEIGNANT = "enseignant";
	public static final String ATT_FORM = "form";
	public static final String SESSION_ENSEIGNANTS = "enseignants";
	public static final String VUE_SUCCES = "/WEB-INF/afficherEnseignant.jsp";
	public static final String VUE_FORM = "/WEB-INF/creerEnseignant.jsp";

	private EnseignantDao enseignantDao;

	public void init() throws ServletException {

		/* Récupération d'une instance de notre DAO Utilisateur */
		this.enseignantDao = ((DAOFactory) (getServletContext()
				.getAttribute(CONF_DAO_FACTORY))).getEnseignantDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		this.getServletContext().getRequestDispatcher(VUE_FORM)
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		CreationEnseignantForm form = new CreationEnseignantForm(enseignantDao);

		/*
		 * Traitement de la requête et récupération du bean en résultant
		 */
		Enseignant enseignant = form.creerEnseignant(request);

		/* Ajout du bean et de l'objet métier à l'objet requête */
		request.setAttribute(ATT_ENSEIGNANT, enseignant);
		request.setAttribute(ATT_FORM, form);

		/* Si aucune erreur */
		if (form.getErreurs().isEmpty()) {

			/*
			 * Alors récupération de la map des clients dans la session
			 */
			HttpSession session = request.getSession();
			Map<Long, Enseignant> enseignants = (HashMap<Long, Enseignant>) session
					.getAttribute(SESSION_ENSEIGNANTS);

			/*
			 * Si aucune map n'existe, alors initialisation d'une nouvelle map
			 */
			if (enseignants == null) {
				enseignants = new HashMap<Long, Enseignant>();
			}

			/* Puis ajout du client courant dans la map */
			enseignants.put(enseignant.getId(), enseignant);

			/* Et enfin (ré)enregistrement de la map en session */
			session.setAttribute(SESSION_ENSEIGNANTS, enseignants);

			/* Affichage de la fiche récapitulative */
			this.getServletContext().getRequestDispatcher(VUE_SUCCES)
					.forward(request, response);
		} else {
			/*
			 * Sinon, ré-affichage du formulaire de création avec les erreurs
			 */
			this.getServletContext().getRequestDispatcher(VUE_FORM)
					.forward(request, response);
		}

	}
}
