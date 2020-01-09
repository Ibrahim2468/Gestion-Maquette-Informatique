package com.sdzee.tp.forms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sdzee.tp.beans.Salle;
import com.sdzee.tp.dao.DAOException;
import com.sdzee.tp.dao.SalleDao;

public class CreationSalleForm {

	private static final String CHAMP_NOM = "nomSalle";

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	private SalleDao salleDao;

	public CreationSalleForm(SalleDao salleDao) {
		this.salleDao = salleDao;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public String getResultat() {
		return resultat;
	}

	public Salle creerSalle(HttpServletRequest request) {

		String nom = getValeurChamp(request, CHAMP_NOM);

		System.out.println("Le nom est : " + nom);

		Salle salle = new Salle();

		traiterNom(nom, salle);

		System.out.println("Les Creer sont : ");
		System.out.println(salle.getNom_salle());
		try {
			if (erreurs.isEmpty()) {

				salleDao.creer(salle);

				resultat = "Succ�s de la cr�ation de la salle.";

			} else {
				resultat = "�chec de la cr�ation de la salle.";
			}
		} catch (DAOException e) {
			setErreur("impr�vu", "Erreur impr�vue lors de la cr�ation.");
			resultat = "�chec de la cr�ation de la salle : une erreur impr�vue est survenue, merci de r�essayer dans quelques instants.";
			e.printStackTrace();
		}

		return salle;

	}

	public Salle trouverSalle(int id) {

		Salle salle = new Salle();

		// try {
		// if (erreurs.isEmpty()) {
		salle = salleDao.trouver(id);
		resultat = "Succ�s de la recherche de la salle.";
		// } else {
		// resultat = "�chec de la recherche du classe.";
		// }
		// } catch (DAOException e) {
		// setErreur("impr�vu", "Erreur impr�vue lors de la recherche.");
		// resultat =
		// "�chec de la recherche du classe : une erreur impr�vue est survenue, merci de r�essayer dans quelques instants.";
		// e.printStackTrace();
		// }

		return salle;
	}

	public List<Salle> listerSalles() {

		List<Salle> salles = new ArrayList<Salle>();

		// try {
		// if (erreurs.isEmpty()) {

		salles = salleDao.lister();
		// resultat = "Succ�s de la recherche de la classe.";
		// } else {
		// resultat = "�chec de la recherche du classe.";
		// }
		// } catch (DAOException e) {
		// setErreur("impr�vu", "Erreur impr�vue lors de la recherche.");
		// resultat =
		// "�chec de la recherche du classe : une erreur impr�vue est survenue, merci de r�essayer dans quelques instants.";
		// e.printStackTrace();
		// }

		return salles;
	}

	public void supprimerSalle(int id) {

		Salle salle = trouverSalle(id);

		// try {
		// if (erreurs.isEmpty()) {
		salleDao.supprimer(salle);
		resultat = "Succ�s de la suppression de la salle.";
		// } else {
		// resultat = "�chec de la suppression du classe.";
		// }
		// } catch (DAOException e) {
		// setErreur("impr�vu", "Erreur impr�vue lors de la suppression.");
		// resultat =
		// "�chec de la suppression de la classe : une erreur impr�vue est survenue, merci de r�essayer dans quelques instants.";
		// e.printStackTrace();
		// }

	}

	public void modifierSalle(int id, String nom) {

		Salle salle = trouverSalle(id);

		// try {
		// if (erreurs.isEmpty()) {

		salleDao.update(salle, nom);
		resultat = "Succ�s de la modification de la salle.";

		// } else {
		// resultat = "�chec de la modification du classe.";
		// }
		// } catch (DAOException e) {
		// setErreur("impr�vu", "Erreur impr�vue lors de la modification.");
		// resultat =
		// "�chec de la modification de la  classe : une erreur impr�vue est survenue, merci de r�essayer dans quelques instants.";
		// e.printStackTrace();
		// }

	}

	private void traiterNom(String nom, Salle salle) {
		try {
			validationNom(nom);
		} catch (FormValidationException e) {
			setErreur(CHAMP_NOM, e.getMessage());
		}
		salle.setNom_salle(nom);
	}

	private void validationNom(String nom) throws FormValidationException {
		if (nom != null) {
			if (nom.length() < 3) {
				throw new FormValidationException(
						"Le nom de la salle doit contenir au moins 3 caract�res.");
			}
		} else {
			throw new FormValidationException("Merci d'entrer un nom de salle.");
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
