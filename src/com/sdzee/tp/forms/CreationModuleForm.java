package com.sdzee.tp.forms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sdzee.tp.beans.Module;
import com.sdzee.tp.beans.Semestre;
import com.sdzee.tp.dao.ClasseDao;
import com.sdzee.tp.dao.ModuleDao;
import com.sdzee.tp.dao.SemestreDao;

public class CreationModuleForm {

	private static final String CHAMP_NOM = "nom";
	private static final String CHAMP_SEMESTRE = "semestre";

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	private ModuleDao moduleDao;
	private SemestreDao semestreDao;
	private ClasseDao classeDao;

	public CreationModuleForm(ModuleDao moduleDao, SemestreDao semestreDao,
			ClasseDao classeDao) {
		// TODO Auto-generated constructor stub

		this.moduleDao = moduleDao;
		this.semestreDao = semestreDao;
		this.classeDao = classeDao;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public String getResultat() {
		return resultat;
	}

	public Module creerModule(HttpServletRequest request) {

		String nom = getValeurChamp(request, CHAMP_NOM);
		int id_semestre = Integer.valueOf(
				getValeurChamp(request, CHAMP_SEMESTRE)).intValue();

		Module module = new Module();

		traiterNom(nom, module);
		traiterSemestre(id_semestre, module);

		// try {
		// if (erreurs.isEmpty()) {

		moduleDao.creer(module);

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

		return module;

	}

	public Module trouverModule(int id) {

		Module module = new Module();

		// try {
		// if (erreurs.isEmpty()) {
		module = moduleDao.trouver(id, semestreDao, classeDao);
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

		return module;
	}

	public List<Module> listerModules() {

		List<Module> modules = new ArrayList<Module>();

		// try {
		// if (erreurs.isEmpty()) {

		modules = moduleDao.lister(semestreDao, classeDao);
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

		return modules;
	}

	public void supprimerModule(int id) {

		Module module = trouverModule(id);

		// try {
		// if (erreurs.isEmpty()) {
		moduleDao.supprimer(module);
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

	public void modifierModule(int id, String nom, int id_semestre) {

		Module module = trouverModule(id);

		// try {
		// if (erreurs.isEmpty()) {

		moduleDao.update(module, nom, id_semestre);
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

	private void traiterNom(String nom, Module module) {
		try {
			validationNom(nom);
		} catch (FormValidationException e) {
			setErreur(CHAMP_NOM, e.getMessage());
		}
		module.setNom_module(nom);
	}

	private void traiterSemestre(int id, Module module) {
		Semestre semestre = null;
		try {
			semestre = validationSemestre(id);
		} catch (FormValidationException e) {
			setErreur(CHAMP_SEMESTRE, e.getMessage());
		}
		module.setSemestre(semestre);
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

	private Semestre validationSemestre(int id) throws FormValidationException {

		Semestre semestre = semestreDao.trouver(id, classeDao);

		return semestre;
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
