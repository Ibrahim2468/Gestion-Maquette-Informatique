package com.sdzee.tp.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sdzee.tp.beans.ChefDepartement;
import com.sdzee.tp.beans.Classe;
import com.sdzee.tp.beans.Enseignant;
import com.sdzee.tp.dao.ConnectionDao;

public final class ConnectionForm {

	private static final String CHAMP_IDENTIFIANT = "identifiant";
	private static final String CHAMP_PASS = "passe";

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	private ConnectionDao connectionDao;

	public ConnectionForm(ConnectionDao connectionDao) {
		this.connectionDao = connectionDao;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public String getResultat() {
		return resultat;
	}

	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}

	private void traiterIdentifiant(String identifiant) {
		try {
			validationIdentifiant(identifiant);
		} catch (FormValidationException e) {
			setErreur(CHAMP_IDENTIFIANT, e.getMessage());
		}
	}

	private void traiterPasse(String passe) {
		try {
			validationPasse(passe);
		} catch (FormValidationException e) {
			setErreur(CHAMP_PASS, e.getMessage());
		}
	}

	private void validationIdentifiant(String identifiant)
			throws FormValidationException {
		if (identifiant != null) {
			if (identifiant.length() < 3) {
				throw new FormValidationException(
						"       <i class='material-icons'>report_problem</i> L'identifiant doit contenir au moins 3 caractères.");
			}
		} else {
			throw new FormValidationException(
					"       <i class='material-icons'>report_problem</i> Merci d'entrer un identifiant.");
		}
	}

	private void validationPasse(String passe) throws FormValidationException {
		if (passe != null) {
			if (passe.length() < 4) {
				throw new FormValidationException(
						"       <i class='material-icons'>report_problem</i> Mot de passe trop court.");
			}
		} else {
			throw new FormValidationException(
					"       <i class='material-icons'>report_problem</i> Merci d'entrer un mot de passe.");
		}
	}

	/*
	 * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
	 * sinon.
	 */
	private static String getValeurChamp(HttpServletRequest request,
			String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur;
		}
	}

	public Object creerConnection(HttpServletRequest request) {

		String identifiant = getValeurChamp(request, CHAMP_IDENTIFIANT);
		String passe = getValeurChamp(request, CHAMP_PASS);

		traiterIdentifiant(identifiant);
		traiterPasse(passe);

		if (erreurs.isEmpty()) {

			Classe classe = connectionDao.trouver_classe(identifiant, passe);

			Enseignant enseignant = connectionDao.trouver_enseignant(
					identifiant, passe);

			ChefDepartement chef = connectionDao.trouver_chef(identifiant,
					passe);

			if (chef != null && chef.getIdentifiant_chef().equals(identifiant)
					&& chef.getPasse_chef().equals(passe)) {

				return chef;
			}

			if (enseignant != null
					&& enseignant.getIdentifiant_enseignant().equals(
							identifiant)
					&& enseignant.getPasse_enseignant().equals(passe)) {

				return enseignant;
			}

			if (classe != null
					&& classe.getIdentifiant_classe().equals(identifiant)
					&& classe.getPasse_classe().equals(passe)) {

				return classe;
			}
		}

		return null;

	}
}
