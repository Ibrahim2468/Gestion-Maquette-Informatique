package com.sdzee.tp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.tp.beans.ChefDepartement;
import com.sdzee.tp.beans.Classe;
import com.sdzee.tp.beans.Enseignant;
import com.sdzee.tp.dao.ConnectionDao;
import com.sdzee.tp.dao.DAOFactory;
import com.sdzee.tp.forms.ConnectionForm;

/**
 * Servlet implementation class Connection
 */
@WebServlet("/Connection")
public class Connection extends HttpServlet {

	public static final String CONF_DAO_FACTORY = "daofactory";
	private static final String VUE_DEFAULT = "/WEB-INF/connection.jsp";
	private static final String VUE_CHEF = "ChefDeDepartement";
	private static final String VUE_ENSEIGNANT = "Enseignant";
	private static final String VUE_CLASSE = "CreationCour";
	public static final String SESSION_CONNEXION = "connexion";

	private ConnectionDao connectionDao;

	public void init() throws ServletException {
		/* Récupération d'une instance de notre DAO Utilisateur */
		this.connectionDao = ((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getConnectionDao();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

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

		ConnectionForm form = new ConnectionForm(connectionDao);

		Object connection = form.creerConnection(request);

		// System.out.println(connection.getClass().getName());

		if (form.creerConnection(request) != null
				&& (form.creerConnection(request).getClass().getName())
						.equals("com.sdzee.tp.beans.ChefDepartement")) {

			ChefDepartement chef = (ChefDepartement) form
					.creerConnection(request);

			request.setAttribute("chef", chef);

			response.sendRedirect(VUE_CHEF);

		}

		else if (form.creerConnection(request) != null
				&& (form.creerConnection(request).getClass().getName())
						.equals("com.sdzee.tp.beans.Classe")) {

			Classe classe = (Classe) form.creerConnection(request);

			System.out.println("La classe est : " + classe.getId_classe());
			request.setAttribute("classe", classe);

			response.sendRedirect(VUE_CLASSE + "?val=" + classe.getId_classe());
		}

		else if (form.creerConnection(request) != null
				&& (form.creerConnection(request).getClass().getName())
						.equals("com.sdzee.tp.beans.Enseignant")) {

			Enseignant enseignant = (Enseignant) form.creerConnection(request);

			request.setAttribute("enseignant", enseignant);

			response.sendRedirect(VUE_ENSEIGNANT + "?val=" + enseignant.getId());

		} else {
			if (form.getErreurs().isEmpty()) {
				request.setAttribute(
						"pas",
						" <i class='material-icons prefix'>report_problem</i> Mot de passe ou identifiant incorrect.");

			} else {
				request.setAttribute("erreurs", form.getErreurs());
			}
			this.getServletContext().getRequestDispatcher(VUE_DEFAULT)
					.forward(request, response);
		}
	}
}
