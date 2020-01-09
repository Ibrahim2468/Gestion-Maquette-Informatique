package com.sdzee.tp.forms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sdzee.tp.beans.ChefDepartement;
import com.sdzee.tp.beans.Classe;
import com.sdzee.tp.beans.Enseignant;
import com.sdzee.tp.dao.OublieDao;
import com.sdzee.tp.mail.ClassEmail;

public class OublieForm {

	private static final String CHAMP_EMAIL = "email";

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	private OublieDao oublieDao;

	public OublieForm(OublieDao oublieDao) {
		// TODO Auto-generated constructor stub
		this.oublieDao = oublieDao;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public String getResultat() {
		return resultat;
	}

	/*
	 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
	 */
	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}

	private void traiterEmail(String email) {
		try {
			validationEmail(email);
		} catch (FormValidationException e) {
			setErreur(CHAMP_EMAIL, e.getMessage());
		}
	}

	private static String getValeurChamp(HttpServletRequest request,
			String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur;
		}
	}

	private void validationEmail(String email) throws FormValidationException {
		if (email != null
				&& !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
			throw new FormValidationException(
					"Merci de saisir une adresse mail valide.");
		}
	}

	public int creerOublie(HttpServletRequest request) {

		String email = getValeurChamp(request, CHAMP_EMAIL);

		traiterEmail(email);

		List<ChefDepartement> chefs = oublieDao.trouverChefDepartement(email);
		List<Enseignant> enseignants = oublieDao.trouverEnseignant(email);
		List<Classe> classes = oublieDao.trouverClasse(email);

		if (!chefs.isEmpty()) {
			for (int i = 0; i < chefs.size(); i++) {

				String message = "Salut " + chefs.get(i).getNom_chef() + " "
						+ chefs.get(i).getPrenom_chef() + "\n"
						+ "Votre identifiant est "
						+ chefs.get(i).getIdentifiant_chef()
						+ "\nVotre mot de passe est "
						+ chefs.get(i).getPasse_chef();

				ClassEmail b = new ClassEmail(email, message);
			}
			return 1;
		}

		else if (!enseignants.isEmpty()) {
			for (int i = 0; i < enseignants.size(); i++) {

				String message = "Salut "
						+ enseignants.get(i).getNom_enseignant() + " "
						+ enseignants.get(i).getPrenom_enseignant() + "\n"
						+ "Votre identifiant est "
						+ enseignants.get(i).getIdentifiant_enseignant()
						+ "\nVotre mot de passe est "
						+ enseignants.get(i).getPasse_enseignant();

				ClassEmail b = new ClassEmail(email, message);
			}
			return 1;
		}

		else if (!classes.isEmpty()) {
			for (int i = 0; i < classes.size(); i++) {

				String message = "Salut responsable de la "
						+ classes.get(i).getNom_classe() + "\n"
						+ "Votre identifiant est "
						+ classes.get(i).getIdentifiant_classe()
						+ "\nVotre mot de passe est "
						+ classes.get(i).getPasse_classe();

				ClassEmail b = new ClassEmail(email, message);
			}
			return 1;
		}

		else {
			return 0;
		}

	}
}
