package com.sdzee.tp.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sdzee.tp.beans.Matiere;
import com.sdzee.tp.beans.Module;
import com.sdzee.tp.dao.MatiereDao;

public class MatiereForm {

	private static final String CHAMP_INTITULE = "intitule";
	private static final String CHAMP_VOLUME = "volume";
	private static final String CHAMP_MODULE = "module";

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	private MatiereDao matiereDao;

	public MatiereForm(MatiereDao matiereDao) {
		// TODO Auto-generated constructor stub

		this.matiereDao = matiereDao;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public String getResultat() {
		return resultat;
	}

	private void traiterIntitule(String intitule, Matiere matiere) {
		try {
			validationIntitule(intitule);
		} catch (FormValidationException e) {
			setErreur(CHAMP_INTITULE, e.getMessage());
		}
		matiere.setIntitule_matiere(intitule);
	}

	private void traiterVolume(int volume, Matiere matiere) {
		try {
			validationVolume(volume);
		} catch (FormValidationException e) {
			setErreur(CHAMP_VOLUME, e.getMessage());
		}
		matiere.setVolume_matiere(volume);
	}

	// private void traiterModule(int id_module, Matiere matiere) {
	// try {
	// validationModule(module);
	// } catch (FormValidationException e) {
	// setErreur(CHAMP_MODULE, e.getMessage());
	// }
	// matiere.setModule_matiere(module);
	// }

	private void validationIntitule(String intitule)
			throws FormValidationException {
		if (intitule != null) {
			if (intitule.length() < 5) {
				throw new FormValidationException(
						"L'intitule de la matiere doit contenir au moins 5 caractères.");
			}
		} else {
			throw new FormValidationException("Merci d'entrer un intitule.");
		}
	}

	private void validationVolume(int volume) throws FormValidationException {

		if (volume < 20 || volume > 60) {
			throw new FormValidationException(
					"Le volume doit etre compris entre 20 et 60 heures.");

		}
	}

	private void validationModule(Module module) throws FormValidationException {
		if (module == null) {
			throw new FormValidationException("Veillez selectionner un module");
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

	public Matiere creerMatiere(HttpServletRequest request) {

		String intitule = getValeurChamp(request, CHAMP_INTITULE);
		int volume = Integer.valueOf(getValeurChamp(request, CHAMP_VOLUME))
				.intValue();
		int id_module = Integer.valueOf(getValeurChamp(request, CHAMP_MODULE))
				.intValue();

		Matiere matiere = new Matiere();

		traiterIntitule(intitule, matiere);
		traiterVolume(volume, matiere);

		return null;
	}
}
