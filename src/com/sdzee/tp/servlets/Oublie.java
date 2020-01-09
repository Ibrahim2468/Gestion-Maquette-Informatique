package com.sdzee.tp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdzee.tp.dao.DAOFactory;
import com.sdzee.tp.dao.OublieDao;
import com.sdzee.tp.forms.OublieForm;

/**
 * Servlet implementation class Oublie
 */
@WebServlet("/Oublie")
public class Oublie extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VUE_DEFAULT = "/WEB-INF/oublie.jsp";
	private static final String VUE_SUCCES = "Connection";
	public static final String CONF_DAO_FACTORY = "daofactory";

	private OublieDao oublieDao;

	public void init() throws ServletException {
		/* Récupération d'une instance de notre DAO Utilisateur */
		this.oublieDao = ((DAOFactory) getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getOublieDao();
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

		OublieForm form = new OublieForm(oublieDao);

		int recup = form.creerOublie(request);

		if (recup == 0) {
			request.setAttribute("pas",
					"<i class='material-icons'>report_problem</i>  Cet email est inconnu.");
			this.getServletContext().getRequestDispatcher(VUE_DEFAULT)
					.forward(request, response);
		} else {
			response.sendRedirect(VUE_SUCCES);
		}

	}
}
