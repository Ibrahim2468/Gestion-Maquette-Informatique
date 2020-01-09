package com.sdzee.tp.forms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sdzee.tp.beans.Matiere;
import com.sdzee.tp.beans.Module;
import com.sdzee.tp.dao.ClasseDao;
import com.sdzee.tp.dao.MatiereDao;
import com.sdzee.tp.dao.ModuleDao;
import com.sdzee.tp.dao.SemestreDao;

public class CreationMatiereForm {

	private static final String CHAMP_INTITULE = "intitule";
	private static final String CHAMP_VOLUME = "volume";
	private static final String CHAMP_MODULE = "module";

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	private MatiereDao matiereDao;
	private ModuleDao moduleDao;
	private SemestreDao semestreDao;
	private ClasseDao classeDao;

	public CreationMatiereForm(MatiereDao matiereDao, ModuleDao moduleDao,
			SemestreDao semestreDao, ClasseDao classeDao) {
		// TODO Auto-generated constructor stub

		this.matiereDao = matiereDao;
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

	public Matiere creerMatiere(HttpServletRequest request) {

		String intitule = getValeurChamp(request, CHAMP_INTITULE);
		String volume = getValeurChamp(request, CHAMP_VOLUME);
		int id_module = Integer.valueOf(getValeurChamp(request, CHAMP_MODULE))
				.intValue();

		Matiere matiere = new Matiere();

		traiterIntitule(intitule, matiere);
		traiterVolume(volume, matiere);
		traiterModule(id_module, matiere);
		// try {
		// if (erreurs.isEmpty( {
		//
		matiereDao.creer(matiere);
		//
		// // St//ng message =
		// //
		// "Salut C'est le chef de departement de la LGI.\nVous avez été ddesigné comme responsable de classe.\nVos parrametres son//: \nIdentifiant : "
		// // + classe.g//Identifiant_c//sse()
		// // + "\n"
		// // // + "Password : "
		// // + //asse.g//Passe_classe();
		// //
		// // ClassEmail b = new //as//mail(email, message);
		//
		// resultat = "Succès d//la//réation de la m//iere.";
		//
		// // } else {
		// // resultat = "Éc//c de la //éation de la classe.";
		// // }// // // } catch (DAOException e) {
		// // setErreur("imprévu", "Er//ur imprévue lors // la création.");
		// // resultat =
		// //
		// "Échec de la création de la classe : une erreur imprévue est
		// survenue,//erci de réessayer dans quel//es insta//s.//
		// // e.printStackTrace();
		// // }
		//
		return matiere;

	}

	public Matiere trouverMatiere(int id) {

		Matiere matiere = new Matiere();

		// try {
		// if (erreurs.isEmpty()) {
		matiere = matiereDao.trouver(id, moduleDao, semestreDao, classeDao);
		// module = moduleDao.trouver(id, semestreDao, classeDao);
		resultat = "Succès de la recherche de la matiere.";
		// } else {
		// resultat = "Échec de la recherche du classe.";
		// }
		// } catch (DAOException e) {
		// setErreur("imprévu", "Erreur imprévue lors de la recherche.");
		// resultat =
		// "Échec de la recherche du classe : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
		// e.printStackTrace();
		// }

		return matiere;
	}

	public List<Matiere> listerMatieres() {

		List<Matiere> matieres = new ArrayList<Matiere>();

		// try {
		// if (erreurs.isEmpty()) {

		matieres = matiereDao.lister(moduleDao, semestreDao, classeDao);
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

		return matieres;
	}

	public List<Matiere> listerMatieresRestants() {

		List<Matiere> matieres = new ArrayList<Matiere>();

		// try {
		// if (erreurs.isEmpty()) {

		matieres = matiereDao.listerRestant(moduleDao, semestreDao, classeDao);
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

		return matieres;
	}

	public void supprimerMatiere(int id) {

		Matiere matiere = trouverMatiere(id);

		// try {
		// if (erreurs.isEmpty()) {
		matiereDao.supprimer(matiere);
		resultat = "Succès de la suppression du matiere.";
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

	public void modifierMatiere(int id, String intitule, int volume,
			int id_module) {

		Matiere matiere = trouverMatiere(id);

		// try {
		// if (erreurs.isEmpty()) {

		matiereDao.update(matiere, intitule, volume, id_module);
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

	private void traiterIntitule(String intitule, Matiere matiere) {
		try {
			validationIntitule(intitule);
		} catch (FormValidationException e) {
			setErreur(CHAMP_INTITULE, e.getMessage());
		}
		matiere.setIntitule_matiere(intitule);
	}

	private void traiterVolume(String volume, Matiere matiere) {
		try {
			validationVolume(volume);
		} catch (FormValidationException e) {
			setErreur(CHAMP_VOLUME, e.getMessage());
		}
		matiere.setVolume_matiere(Integer.valueOf(volume).intValue());
	}

	private void traiterModule(int id, Matiere matiere) {
		Module module = null;
		try {
			module = validationModule(id);
		} catch (FormValidationException e) {
			setErreur(CHAMP_MODULE, e.getMessage());
		}
		matiere.setModule(module);
	}

	private void validationIntitule(String intitule)
			throws FormValidationException {
		if (intitule != null) {
			if (intitule.length() < 3) {
				throw new FormValidationException(
						"L'intitule de la matiere doit contenir au moins 3 caractères.");
			}
		} else {
			throw new FormValidationException(
					"Merci d'entrer un intitule de matiere.");
		}
	}

	private void validationVolume(String volume) throws FormValidationException {
		if (volume != null) {
			if (!volume.matches("^\\d+$")) {
				throw new FormValidationException(
						"Le volume de la matiere doit uniquement contenir des chiffres.");
			} else if (volume.length() < 2) {
				throw new FormValidationException(
						"Le volume de la matiere doit contenir au moins 2 chiffres.");
			}
		} else {
			throw new FormValidationException(
					"Merci d'entrer un volume de matiere.");
		}
	}

	private Module validationModule(int id) throws FormValidationException {

		Module module = moduleDao.trouver(id, semestreDao, classeDao);

		return module;
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
