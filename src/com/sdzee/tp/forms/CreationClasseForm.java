package com.sdzee.tp.forms;

import static com.sdzee.tp.dao.DAOUtilitaire.identifiant_classe;
import static com.sdzee.tp.dao.DAOUtilitaire.password;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sdzee.tp.beans.Classe;
import com.sdzee.tp.dao.ClasseDao;
import com.sdzee.tp.dao.DAOException;
import com.sdzee.tp.mail.ClassEmail;

public class CreationClasseForm {

	private static final String CHAMP_NOM = "nomClasse";
	private static final String CHAMP_EMAIL = "emailClasse";

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	private ClasseDao classeDao;

	public CreationClasseForm(ClasseDao classeDao) {
		this.classeDao = classeDao;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public String getResultat() {
		return resultat;
	}

	public Classe creerClasse(HttpServletRequest request) {

		String nom = getValeurChamp(request, CHAMP_NOM);
		String email = getValeurChamp(request, CHAMP_EMAIL);

		System.out.println("Le nom est : " + nom);
		System.out.println("Le email est : " + email);

		Classe classe = new Classe();

		traiterNom(nom, classe);
		traiterEmail(email, classe);
		traiterIdentifiant(nom, classe);
		traiterPasse(classe);

		System.out.println("Les Creer sont : ");
		System.out.println(classe.getIdentifiant_classe());
		System.out.println(classe.getPasse_classe());
		System.out.println(classe.getNom_classe());
		System.out.println(classe.getEmail_classe());

		try {
			if (erreurs.isEmpty()) {

				classeDao.creer(classe);

				String message = "Salut C'est le chef de departement de la LGI.\nVous avez été ddesigné comme responsable de classe.\nVos parrametres sont : \nIdentifiant : "
						+ classe.getIdentifiant_classe()
						+ "\n"
						+ "Password : "
						+ classe.getPasse_classe();

				ClassEmail b = new ClassEmail(email, message);

				resultat = "Succès de la création de la classe.";

			} else {
				resultat = "Échec de la création de la classe.";
			}
		} catch (DAOException e) {
			setErreur("imprévu", "Erreur imprévue lors de la création.");
			resultat = "Échec de la création de la classe : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
			e.printStackTrace();
		}

		return classe;

	}

	public Classe trouverClasse(int id) {

		Classe classe = new Classe();

		// try {
		// if (erreurs.isEmpty()) {
		classe = classeDao.trouver(id);
		resultat = "Succès de la recherche de la classe.";
		// } else {
		// resultat = "Échec de la recherche du classe.";
		// }
		// } catch (DAOException e) {
		// setErreur("imprévu", "Erreur imprévue lors de la recherche.");
		// resultat =
		// "Échec de la recherche du classe : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
		// e.printStackTrace();
		// }

		return classe;
	}

	public List<Classe> listerClasses() {

		List<Classe> classes = new ArrayList<Classe>();

		// try {
		// if (erreurs.isEmpty()) {

		classes = classeDao.lister();
		// resultat = "Succès de la recherche de la classe.";
		// } else {
		// resultat = "Échec de la recherche du classe.";
		// }
		// } catch (DAOException e) {
		// setErreur("imprévu", "Erreur imprévue lors de la recherche.");
		// resultat =
		// "Échec de la recherche du classe : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
		// e.printStackTrace();
		// }

		return classes;
	}

	public void supprimerClasse(int id) {

		Classe classe = trouverClasse(id);

		// try {
		// if (erreurs.isEmpty()) {
		classeDao.supprimer(classe);
		resultat = "Succès de la suppression de la classe.";
		// } else {
		// resultat = "Échec de la suppression du classe.";
		// }
		// } catch (DAOException e) {
		// setErreur("imprévu", "Erreur imprévue lors de la suppression.");
		// resultat =
		// "Échec de la suppression de la classe : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
		// e.printStackTrace();
		// }

	}

	public void modifierClasse(int id, String nom, String email) {

		Classe classe = trouverClasse(id);

		// try {
		// if (erreurs.isEmpty()) {

		classeDao.update(classe, nom, email);
		resultat = "Succès de la modification de la classe.";

		// } else {
		// resultat = "Échec de la modification du classe.";
		// }
		// } catch (DAOException e) {
		// setErreur("imprévu", "Erreur imprévue lors de la modification.");
		// resultat =
		// "Échec de la modification de la  classe : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
		// e.printStackTrace();
		// }

	}

	private void traiterNom(String nom, Classe classe) {
		try {
			validationNom(nom);
		} catch (FormValidationException e) {
			setErreur(CHAMP_NOM, e.getMessage());
		}
		classe.setNom_classe(nom);
	}

	private void traiterEmail(String email, Classe classe) {
		try {
			validationEmail(email);
		} catch (FormValidationException e) {
			setErreur(CHAMP_EMAIL, e.getMessage());
		}
		classe.setEmail_classe(email);
	}

	private void traiterIdentifiant(String nom, Classe classe) {
		String id = identifiant_classe(nom);
		classe.setIdentifiant_classe(id);
	}

	private void traiterPasse(Classe classe) {
		String passe = password() + password() + password();
		classe.setPasse_classe(passe);
	}

	private void validationNom(String nom) throws FormValidationException {
		if (nom != null) {
			if (nom.length() < 3) {
				throw new FormValidationException(
						"Le nom de la classe doit contenir au moins 3 caractères.");
			}
		} else {
			throw new FormValidationException(
					"Merci d'entrer un nom de classe.");
		}
	}

	private void validationEmail(String email) throws FormValidationException {
		if (email != null
				&& !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
			throw new FormValidationException(
					"Merci de saisir une adresse mail valide.");
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

}
