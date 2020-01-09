package com.sdzee.tp.forms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sdzee.tp.beans.Enseignant;
import com.sdzee.tp.dao.EnseignantDao;

public class ListerEnseignantForm {

	private EnseignantDao enseignantDao;

	public ListerEnseignantForm(EnseignantDao enseignantDao) {
		this.enseignantDao = enseignantDao;
	}

	public List<Enseignant> recupererEnseignant(HttpServletRequest request) {

		List<Enseignant> enseignants = enseignantDao.lister_tout();
		// System.out.println("AFFICHE ICI");
		// for (int i = 0; i < enseignants.size(); i++) {
		// System.out.println(enseignants.get(i).getId());
		// System.out.println(enseignants.get(i).getNom_enseignant());
		// System.out.println(enseignants.get(i).getPrenom_enseignant());
		// System.out.println(enseignants.get(i).getAdresse_enseignant());
		// System.out.println(enseignants.get(i).getTelephone_enseignant());
		// System.out.println(enseignants.get(i).getEmail_enseignant());
		// System.out.println(enseignants.get(i).getPasse_enseignant());
		// System.out.println(enseignants.get(i).getTitre_enseignant());
		// System.out.println(enseignants.get(i).getDiscipline_enseignant());
		// System.out.println(enseignants.get(i).getGrade_enseignant());
		// System.out.println(enseignants.get(i).getIdentifiant_enseignant());
		// }

		return enseignants;
	}
}
