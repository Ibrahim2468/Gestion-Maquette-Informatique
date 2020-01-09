package com.sdzee.tp.forms;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sdzee.tp.beans.Classe;
import com.sdzee.tp.beans.Cour;
import com.sdzee.tp.beans.Enseignant;
import com.sdzee.tp.beans.Matiere;
import com.sdzee.tp.beans.Salle;
import com.sdzee.tp.dao.ClasseDao;
import com.sdzee.tp.dao.CourDao;
import com.sdzee.tp.dao.DAOException;
import com.sdzee.tp.dao.EnseignantDao;
import com.sdzee.tp.dao.MatiereDao;
import com.sdzee.tp.dao.ModuleDao;
import com.sdzee.tp.dao.SalleDao;
import com.sdzee.tp.dao.SemestreDao;

public class CreationCourForm {

	private static final String CHAMP_NOM = "nom";
	private static final String CHAMP_ENSEIGNANT = "enseignant";
	private static final String CHAMP_CLASSE = "val";
	private static final String CHAMP_SALLE = "salle";
	private static final String CHAMP_MATIERE = "matiere";
	private static final String CHAMP_DATE = "datee";
	private static final String CHAMP_DUREE = "duree";
	private static final String CHAMP_DETAIL = "detail";

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	private CourDao courDao;
	private EnseignantDao enseignantDao;
	private SalleDao salleDao;
	private MatiereDao matiereDao;
	private ModuleDao moduleDao;
	private SemestreDao semestreDao;
	private ClasseDao classeDao;

	public CreationCourForm(CourDao courDao, EnseignantDao enseignantDao,
			SalleDao salleDao, MatiereDao matiereDao, ModuleDao moduleDao,
			SemestreDao semestreDao, ClasseDao classeDao) {
		// TODO Auto-generated constructor stub

		this.courDao = courDao;
		this.enseignantDao = enseignantDao;
		this.salleDao = salleDao;
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

	private void traiterNom(String nom, Cour cour) {
		try {
			validationNom(nom);
		} catch (FormValidationException e) {
			setErreur(CHAMP_NOM, e.getMessage());
		}
		cour.setNom_cour(nom);
	}

	private void traiterEnseignant(int id, Cour cour) {
		Enseignant enseignant = null;
		try {
			enseignant = validationEnseignant(id);
		} catch (FormValidationException e) {
			setErreur(CHAMP_ENSEIGNANT, e.getMessage());
		}
		cour.setEnseignant(enseignant);
	}

	private void traiterSalle(int id, Cour cour) {
		Salle salle = null;
		try {
			salle = validationSalle(id);
		} catch (FormValidationException e) {
			setErreur(CHAMP_SALLE, e.getMessage());
		}
		cour.setSalle(salle);
	}

	private void traiterMatiere(int id, Cour cour) {
		Matiere matiere = null;
		try {
			matiere = validationMatiere(id);
		} catch (FormValidationException e) {
			setErreur(CHAMP_MATIERE, e.getMessage());
		}
		cour.setMatiere(matiere);
	}

	private void traiterClasse(int id, Cour cour) {
		Classe classe = null;
		try {
			classe = validationClasse(id);
		} catch (FormValidationException e) {
			setErreur(CHAMP_ENSEIGNANT, e.getMessage());
		}
		cour.setClasse(classe);
	}

	private void traiterDate(String date, Cour cour) {
		try {
			validationDate(date);
		} catch (FormValidationException e) {
			setErreur(CHAMP_DATE, e.getMessage());
		}
		cour.setDate_cour(date);
	}

	private void traiterDure(String duree, Cour cour) {
		try {
			validationDuree(duree);
		} catch (FormValidationException e) {
			setErreur(CHAMP_DUREE, e.getMessage());
		}
		cour.setDuree_cour(Integer.valueOf(duree).intValue());
	}

	private void traiterDetail(String detail, Cour cour) {
		try {
			validationDetail(detail);
		} catch (FormValidationException e) {
			setErreur(CHAMP_DETAIL, e.getMessage());
		}
		cour.setDetail_cour(detail);
	}

	private void validationNom(String nom) throws FormValidationException {
		if (nom != null) {
			if (nom.length() < 3) {
				throw new FormValidationException(
						"le nom du cour doit contenir au moins 3 caractères.");
			}
		} else {
			throw new FormValidationException("Merci d'entrer un nom de cour.");
		}
	}

	private Enseignant validationEnseignant(int id)
			throws FormValidationException {

		Enseignant enseignant = enseignantDao.trouver(id);

		return enseignant;
	}

	private Salle validationSalle(int id) throws FormValidationException {

		Salle salle = salleDao.trouver(id);

		return salle;
	}

	private Matiere validationMatiere(int id) throws FormValidationException {

		Matiere matiere = matiereDao.trouver(id, moduleDao, semestreDao,
				classeDao);
		System.out.println("La matiere est " + matiere.getId_matiere());

		return matiere;
	}

	private Classe validationClasse(int id) throws FormValidationException {

		Classe classe = classeDao.trouver(id);

		return classe;
	}

	private void validationDate(String date) throws FormValidationException {

		if (date != null) {

			if (convetirDate(date) > dateActuel()) {
				throw new FormValidationException(
						"Veillez choisir une date anterieure.");
			}
		} else {
			throw new FormValidationException(
					"Merci d'entrer une date de cour.");
		}
	}

	private void validationDuree(String duree) throws FormValidationException {
		if (duree != null) {
			if (!duree.matches("^\\d+$")) {
				throw new FormValidationException(
						"La duree du cour doit uniquement contenir des chiffres.");
			} else if (duree.length() < 1) {
				throw new FormValidationException(
						"La duree du cour doit contenir au moins 1 chiffres.");
			}
		} else {
			throw new FormValidationException(
					"Merci d'entrer une duree de cour.");
		}
	}

	private void validationDetail(String detail) throws FormValidationException {
		if (detail != null) {
			if (detail.length() < 3) {
				throw new FormValidationException(
						"Les détail du cour doivent contenir au moins 3 caractères.");
			}
		} else {
			throw new FormValidationException(
					"Merci d'entrer les détails de cour.");
		}
	}

	public Cour creerCour(HttpServletRequest request) {

		String nom = getValeurChamp(request, CHAMP_NOM);
		int id_enseignant = Integer.valueOf(
				getValeurChamp(request, CHAMP_ENSEIGNANT)).intValue();
		int id_classe = Integer.valueOf(getValeurChamp(request, CHAMP_CLASSE))
				.intValue();
		int id_salle = Integer.valueOf(getValeurChamp(request, CHAMP_SALLE))
				.intValue();
		int id_matiere = Integer
				.valueOf(getValeurChamp(request, CHAMP_MATIERE)).intValue();
		String date = getValeurChamp(request, CHAMP_DATE);
		String duree = getValeurChamp(request, CHAMP_DUREE);
		String details = getValeurChamp(request, CHAMP_DETAIL);

		Cour cour = new Cour();

		Matiere matiere = matiereDao.trouver(id_matiere, moduleDao,
				semestreDao, classeDao);
		if (matiere.getRestant_matiere() - Integer.valueOf(duree).intValue() >= 0
				&& matiere.getVolume_matiere() >= matiere.getEffectue_matiere()
						+ Integer.valueOf(duree).intValue()) {

			traiterNom(nom, cour);
			traiterEnseignant(id_enseignant, cour);
			traiterClasse(id_classe, cour);
			traiterMatiere(id_matiere, cour);
			traiterSalle(id_salle, cour);
			traiterDate(date, cour);
			traiterDure(duree, cour);
			traiterDetail(details, cour);
			try {
				if (erreurs.isEmpty()) {
					courDao.creer(cour);
					resultat = "Succès de la création du cour.";
				} else {
					resultat = "Échec de la création du cour.";
				}
			} catch (DAOException e) {
				setErreur("imprévu", "Ereeur imprévue lors de la création.");
				resultat = "Échec de la création du cour : une erreur imprévue est survenue,//Merci de réessayer dans quelques instans.";
				e.printStackTrace();
			}
		}

		return cour;
	}

	public static int convetirDate(String date) {

		String sep = "[' '',']";

		String[] datee = date.split(sep);

		String string = "" + datee[1] + " " + datee[0] + ", " + datee[3];
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
				"MMMM d, yyyy", Locale.ENGLISH);
		LocalDate dateee = LocalDate.parse(string, formatter);
		System.out.println("Le String est : " + dateee.toString());

		String[] dates = dateee.toString().split("-");

		String dateString = "" + dates[0] + dates[1] + dates[2];

		int dateInt = Integer.valueOf(dateString).intValue();

		return dateInt;

	}

	public int dateActuel() {

		String txtDate = new SimpleDateFormat("d-MM-yyyy", Locale.ENGLISH)
				.format(new Date());

		String[] dates = txtDate.split("-");

		String dateString = "" + dates[2] + dates[1] + dates[0];

		int dateInt = Integer.valueOf(dateString).intValue();

		return dateInt;
	}
}
