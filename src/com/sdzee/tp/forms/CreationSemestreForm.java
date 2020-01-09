package com.sdzee.tp.forms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sdzee.tp.beans.Classe;
import com.sdzee.tp.beans.Semestre;
import com.sdzee.tp.dao.ClasseDao;
import com.sdzee.tp.dao.SemestreDao;

public class CreationSemestreForm {

	private static final String CHAMP_NOM = "nom";
	private static final String CHAMP_CLASSE = "classe";

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	private SemestreDao semestreDao;
	private ClasseDao classeDao;

	public CreationSemestreForm(SemestreDao semestreDao, ClasseDao classeDao) {
		this.semestreDao = semestreDao;
		this.classeDao = classeDao;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public String getResultat() {
		return resultat;
	}

	public Semestre creerSemestre(HttpServletRequest request) {

		String nom = getValeurChamp(request, CHAMP_NOM);
		int id_classe = Integer.valueOf(getValeurChamp(request, CHAMP_CLASSE))
				.intValue();

		System.out.println("Le nom est : " + nom);
		System.out.println("L'id est : " + id_classe);

		Semestre semestre = new Semestre();

		traiterNom(nom, semestre);
		traiterClasse(id_classe, semestre);

		System.out.println("Les Creer sont : ");
		System.out.println("Le nom est : " + semestre.getNom_semestre());
		System.out.println("Le nom est : "
				+ semestre.getClasse().getId_classe());

		// try {
		// if (erreurs.isEmpty()) {

		semestreDao.creer(semestre);

		// String message =
		// "Salut C'est le chef de departement de la LGI.\nVous avez été ddesigné comme responsable de classe.\nVos parrametres sont : \nIdentifiant : "
		// + classe.getIdentifiant_classe()
		// + "\n"
		// + "Password : "
		// + classe.getPasse_classe();
		//
		// ClassEmail b = new ClassEmail(email, message);

		resultat = "Succès de la création de la classe.";

		// } else {
		// resultat = "Échec de la création de la classe.";
		// }
		// } catch (DAOException e) {
		// setErreur("imprévu", "Erreur imprévue lors de la création.");
		// resultat =
		// "Échec de la création de la classe : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
		// e.printStackTrace();
		// }

		return semestre;

	}

	public Semestre trouverSemestre(int id) {

		Semestre semestre = new Semestre();

		// try {
		// if (erreurs.isEmpty()) {
		semestre = semestreDao.trouver(id, classeDao);
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

		return semestre;
	}

	public List<Semestre> listerSemestres() {

		List<Semestre> semestres = new ArrayList<Semestre>();

		// try {
		// if (erreurs.isEmpty()) {

		semestres = semestreDao.lister(classeDao);
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

		return semestres;
	}

	public void supprimerSemestre(int id) {

		Semestre semestre = trouverSemestre(id);

		// try {
		// if (erreurs.isEmpty()) {
		semestreDao.supprimer(semestre);
		resultat = "Succès de la suppression du semestre.";
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

	public void modifierSemestre(int id, String nom, int id_classe) {

		Semestre semestre = trouverSemestre(id);

		// try {
		// if (erreurs.isEmpty()) {

		semestreDao.update(semestre, nom, id_classe);
		resultat = "Succès de la modification du semestre.";

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

	private void traiterNom(String nom, Semestre semestre) {
		try {
			validationNom(nom);
		} catch (FormValidationException e) {
			setErreur(CHAMP_NOM, e.getMessage());
		}
		semestre.setNom_semestre(nom);
	}

	private void traiterClasse(int id, Semestre semestre) {
		Classe classe = null;
		try {
			classe = validationClasse(id);
		} catch (FormValidationException e) {
			setErreur(CHAMP_CLASSE, e.getMessage());
		}
		semestre.setClasse(classe);
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

	private Classe validationClasse(int id) throws FormValidationException {

		Classe classe = classeDao.trouver(id);

		return classe;
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
