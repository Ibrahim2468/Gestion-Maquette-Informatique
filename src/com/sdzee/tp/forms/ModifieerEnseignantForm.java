package com.sdzee.tp.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sdzee.tp.beans.Enseignant;
import com.sdzee.tp.dao.DAOException;
import com.sdzee.tp.dao.EnseignantDao;

public class ModifieerEnseignantForm {

	private static final String CHAMP_NOM = "nom";
	private static final String CHAMP_PRENOM = "prenom";
	private static final String CHAMP_ADRESSE = "adresse";
	private static final String CHAMP_TELEPHONE = "telephone";
	private static final String CHAMP_EMAIL = "email";
	private static final String CHAMP_TITRE = "titre";
	private static final String CHAMP_DISCIPLINE = "discipline";
	private static final String CHAMP_GRADE = "grade";

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	private EnseignantDao enseignantDao;

	public ModifieerEnseignantForm(EnseignantDao enseignantDao, int id) {
		this.enseignantDao = enseignantDao;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public String getResultat() {
		return resultat;
	}

	private void traiterNom(String nom, Enseignant enseignant) {
		try {
			validationNom(nom);
		} catch (FormValidationException e) {
			setErreur(CHAMP_NOM, e.getMessage());
		}
		enseignant.setNom_enseignant(nom);
	}

	private void traiterPrenom(String prenom, Enseignant enseignant) {
		try {
			validationPrenom(prenom);
		} catch (FormValidationException e) {
			setErreur(CHAMP_PRENOM, e.getMessage());
		}
		enseignant.setPrenom_enseignant(prenom);
	}

	private void traiterAdresse(String adresse, Enseignant enseignant) {
		try {
			validationAdresse(adresse);
		} catch (FormValidationException e) {
			setErreur(CHAMP_ADRESSE, e.getMessage());
		}
		enseignant.setAdresse_enseignant(adresse);
	}

	private void traiterTelephone(String telephone, Enseignant enseignant) {
		try {
			validationTelephone(telephone);
		} catch (FormValidationException e) {
			setErreur(CHAMP_TELEPHONE, e.getMessage());
		}
		enseignant.setTelephone_enseignant(telephone);
	}

	private void traiterEmail(String email, Enseignant enseignant) {
		try {
			validationEmail(email);
		} catch (FormValidationException e) {
			setErreur(CHAMP_EMAIL, e.getMessage());
		}
		enseignant.setEmail_enseignant(email);
	}

	private void traiterTitre(String titre, Enseignant enseignant) {
		try {
			validationTitre(titre);
		} catch (FormValidationException e) {
			setErreur(CHAMP_TITRE, e.getMessage());
		}
		enseignant.setTitre_enseignant(titre);
	}

	private void traiterDiscipline(String discipline, Enseignant enseignant) {
		try {
			validationDiscipline(discipline);
		} catch (FormValidationException e) {
			setErreur(CHAMP_DISCIPLINE, e.getMessage());
		}
		enseignant.setDiscipline_enseignant(discipline);
	}

	private void traiterGrade(String grade, Enseignant enseignant) {
		try {
			validationGrade(grade);
		} catch (FormValidationException e) {
			setErreur(CHAMP_GRADE, e.getMessage());
		}
		enseignant.setGrade_enseignant(grade);
	}

	// private void traiterIdentifiant(Enseignant enseignant) {
	// enseignant.setIdentifiant_enseignant(identifiant(
	// enseignant.getNom_enseignant(),
	// enseignant.getPrenom_enseignant()));
	// }

	// private void traiterPasse(Enseignant enseignant) {
	// enseignant.setPasse_enseignant(password() + password() + password());
	// }

	private void validationNom(String nom) throws FormValidationException {
		if (nom != null) {
			if (nom.length() < 2) {
				throw new FormValidationException(
						"Le nom d'utilisateur doit contenir au moins 2 caractères.");
			}
		} else {
			throw new FormValidationException(
					"Merci d'entrer un nom d'utilisateur.");
		}
	}

	private void validationPrenom(String prenom) throws FormValidationException {
		if (prenom != null && prenom.length() < 2) {
			throw new FormValidationException(
					"Le prénom d'utilisateur doit contenir au moins 2 caractères.");
		}
	}

	private void validationAdresse(String adresse)
			throws FormValidationException {
		if (adresse != null) {
			if (adresse.length() < 10) {
				throw new FormValidationException(
						"L'adresse de livraison doit contenir au moins 10 caractères.");
			}
		} else {
			throw new FormValidationException(
					"Merci d'entrer une adresse de livraison.");
		}
	}

	private void validationTelephone(String telephone)
			throws FormValidationException {
		if (telephone != null) {
			if (!telephone.matches("^\\d+$")) {
				throw new FormValidationException(
						"Le numéro de téléphone doit uniquement contenir des chiffres.");
			} else if (telephone.length() < 4) {
				throw new FormValidationException(
						"Le numéro de téléphone doit contenir au moins 4 chiffres.");
			}
		} else {
			throw new FormValidationException(
					"Merci d'entrer un numéro de téléphone.");
		}
	}

	private void validationEmail(String email) throws FormValidationException {
		if (email != null
				&& !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
			throw new FormValidationException(
					"Merci de saisir une adresse mail valide.");
		}
	}

	private void validationTitre(String titre) throws FormValidationException {
		if (titre != null) {
			if (titre.length() < 2) {
				throw new FormValidationException(
						"Le titre d'utilisateur doit contenir au moins 2 caractères.");
			}
		} else {
			throw new FormValidationException(
					"Merci d'attribué un titre à l'utilisateur.");
		}
	}

	private void validationDiscipline(String discipline)
			throws FormValidationException {
		if (discipline != null) {
			if (discipline.length() < 2) {
				throw new FormValidationException(
						"La discipline d'un utilisateur doit contenir au moins 2 caractères.");
			}
		} else {
			throw new FormValidationException(
					"Merci d'attribué une discipline à l'utilisateur.");
		}
	}

	private void validationGrade(String grade) throws FormValidationException {
		if (grade != null) {
			if (grade.length() < 2) {
				throw new FormValidationException(
						"La grade d'un utilisateur doit contenir au moins 2 caractères.");
			}
		} else {
			throw new FormValidationException(
					"Merci d'attribué une grade à l'utilisateur.");
		}
	}

	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
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

	public Enseignant creerEnseignant(HttpServletRequest request, int id) {

		String nom = getValeurChamp(request, CHAMP_NOM);
		String prenom = getValeurChamp(request, CHAMP_PRENOM);
		String adresse = getValeurChamp(request, CHAMP_ADRESSE);
		String telephone = getValeurChamp(request, CHAMP_TELEPHONE);
		String email = getValeurChamp(request, CHAMP_EMAIL);
		String titre = getValeurChamp(request, CHAMP_TITRE);
		String discipline = getValeurChamp(request, CHAMP_DISCIPLINE);
		String grade = getValeurChamp(request, CHAMP_GRADE);

		Enseignant enseignant = new Enseignant();

		traiterNom(nom, enseignant);
		traiterPrenom(prenom, enseignant);
		traiterAdresse(adresse, enseignant);
		traiterTelephone(telephone, enseignant);
		traiterEmail(email, enseignant);
		traiterTitre(titre, enseignant);
		traiterDiscipline(discipline, enseignant);
		traiterGrade(grade, enseignant);

		System.out.println("les recup sont : ");
		System.out.println(enseignant.getNom_enseignant());
		System.out.println(enseignant.getPrenom_enseignant());
		System.out.println(enseignant.getAdresse_enseignant());
		System.out.println(enseignant.getTelephone_enseignant());
		System.out.println(enseignant.getEmail_enseignant());
		System.out.println(enseignant.getTitre_enseignant());
		System.out.println(enseignant.getDiscipline_enseignant());
		System.out.println(enseignant.getGrade_enseignant());
		System.out.println(enseignant.getPasse_enseignant());
		System.out.println(enseignant.getIdentifiant_enseignant());

		try {
			if (erreurs.isEmpty()) {

				enseignantDao.modifier(id, enseignant);

				// String message = "Salut " + enseignant.getNom_enseignant()
				// + " " + enseignant.getPrenom_enseignant() + "\n"
				// + "Votre identifiant est "
				// + enseignant.getIdentifiant_enseignant()
				// + "\nVotre mot de passe est "
				// + enseignant.getPasse_enseignant();
				//
				// System.out.println("Mail pres.");
				//
				// ClassEmail b = new ClassEmail(email, message);
				//
				// System.out.println("Mail pres.");

				resultat = "Succès de la création du client.";

			} else {
				resultat = "Échec de la création du client.";
			}
		} catch (DAOException e) {
			setErreur("imprévu", "Erreur imprévue lors de la création.");
			resultat = "Échec de la création du client : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
			e.printStackTrace();
		}
		return enseignant;
	}

	public String password() {

		long randomEntier = (long) (Math.random() * (100000000));
		int Divise;

		String alphabet[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
				"k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
				"w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H",
				"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
				"U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4", "5", "6",
				"7", "8", "9", "0", "@", "&", "$", "a", "b", "c", "d", "e",
				"f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q",
				"r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C",
				"D", "E", "F", "G", "H", "I" };

		String randomChaine = "";

		Divise = (int) (randomEntier % 100);
		randomEntier = randomEntier / 100;
		randomChaine += alphabet[Divise];

		Divise = (int) (randomEntier % 100);
		randomEntier = randomEntier / 100;
		randomChaine += alphabet[Divise];

		Divise = (int) (randomEntier % 100);
		randomEntier = (int) randomEntier / 100;
		randomChaine += alphabet[Divise];

		return randomChaine;
	}

	public String identifiant(String nom, String prenom) {

		String retour = ("" + prenom.charAt(0) + "" + nom.charAt(0) + "" + nom);
		return retour;
	}

}
