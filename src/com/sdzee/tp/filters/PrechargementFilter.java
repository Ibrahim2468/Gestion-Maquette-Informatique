package com.sdzee.tp.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sdzee.tp.beans.Client;
import com.sdzee.tp.beans.Commande;
import com.sdzee.tp.beans.Enseignant;
import com.sdzee.tp.dao.ClientDao;
import com.sdzee.tp.dao.CommandeDao;
import com.sdzee.tp.dao.DAOFactory;
import com.sdzee.tp.dao.EnseignantDao;

public class PrechargementFilter implements Filter {
	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_SESSION_CLIENTS = "clients";
	public static final String ATT_SESSION_COMMANDES = "commandes";
	public static final String ATT_SESSION_ENSEIGNANTS = "enseignants";
	private EnseignantDao enseignantDao;
	private ClientDao clientDao;
	private CommandeDao commandeDao;

	public void init(FilterConfig config) throws ServletException {

		/*
		 * R�cup�ration d'une instance de nos DAO Client et Commande
		 */
		this.clientDao = ((DAOFactory) config.getServletContext().getAttribute(
				CONF_DAO_FACTORY)).getClientDao();
		this.commandeDao = ((DAOFactory) config.getServletContext()
				.getAttribute(CONF_DAO_FACTORY)).getCommandeDao();
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		/* Cast de l'objet request */
		HttpServletRequest request = (HttpServletRequest) req;

		/* R�cup�ration de la session depuis la requ�te */
		HttpSession session = request.getSession();
		/*
		 * Si la map des clients n'existe pas en session, alors l'utilisateur se
		 * connecte pour la premi�re fois et nous devons pr�charger en session
		 * les infos contenues dans la BDD.
		 */

		if (session.getAttribute(ATT_SESSION_CLIENTS) == null) {
			/*
			 * R�cup�ration de la liste des clients existants, et enregistrement
			 * en session
			 */
			List<Client> listeClients = clientDao.lister();
			Map<Long, Client> mapClients = new HashMap<Long, Client>();
			for (Client client : listeClients) {
				mapClients.put(client.getId(), client);
			}
			session.setAttribute(ATT_SESSION_CLIENTS, mapClients);
		}
		/*
		 * De m�me pour la map des commandes
		 */
		if (session.getAttribute(ATT_SESSION_COMMANDES) == null) {
			/*
			 * R�cup�ration de la liste des commandes existantes, et
			 * enregistrement en session
			 */
			List<Commande> listeCommandes = commandeDao.lister();
			Map<Long, Commande> mapCommandes = new HashMap<Long, Commande>();
			for (Commande commande : listeCommandes) {
				mapCommandes.put(commande.getId(), commande);
			}
			session.setAttribute(ATT_SESSION_COMMANDES, mapCommandes);
		}

		if (session.getAttribute(ATT_SESSION_ENSEIGNANTS) == null) {
			/*
			 * R�cup�ration de la liste des commandes existantes, et
			 * enregistrement en session
			 */
			List<Enseignant> listeEnseignants = enseignantDao.lister();
			Map<Long, Enseignant> mapEnseignants = new HashMap<Long, Enseignant>();
			for (Enseignant enseignant : listeEnseignants) {
				mapEnseignants.put(enseignant.getId(), enseignant);
			}
			session.setAttribute(ATT_SESSION_ENSEIGNANTS, mapEnseignants);
		}

		/* Pour terminer, poursuite de la requ�te en cours */
		chain.doFilter(request, res);
	}

	public void destroy() {
	}
}