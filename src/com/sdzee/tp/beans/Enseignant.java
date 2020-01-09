package com.sdzee.tp.beans;

public class Enseignant {

	private long id_enseignant;
	private String identifiant_enseignant;
	private String passe_enseignant;
	private String nom_enseignant;
	private String prenom_enseignant;
	private String email_enseignant;
	private String adresse_enseignant;
	private String telephone_enseignant;
	private String titre_enseignant;
	private String discipline_enseignant;
	private String grade_enseignant;

	public Enseignant() {
		// TODO Auto-generated constructor stub
	}

	public Enseignant(long id_enseignant, String identifiant_enseignant,
			String passe_enseignant, String nom_enseignant,
			String prenom_enseignant, String email_enseignant,
			String adresse_enseignant, String telephone_enseignant,
			String titre_enseignant, String discipline_enseignant,
			String grade_enseignant) {
		super();
		this.id_enseignant = id_enseignant;
		this.identifiant_enseignant = identifiant_enseignant;
		this.passe_enseignant = passe_enseignant;
		this.nom_enseignant = nom_enseignant;
		this.prenom_enseignant = prenom_enseignant;
		this.email_enseignant = email_enseignant;
		this.adresse_enseignant = adresse_enseignant;
		this.telephone_enseignant = telephone_enseignant;
		this.titre_enseignant = titre_enseignant;
		this.discipline_enseignant = discipline_enseignant;
		this.grade_enseignant = grade_enseignant;
	}

	public long getId() {
		return id_enseignant;
	}

	public void setId(long id_enseignant) {
		this.id_enseignant = id_enseignant;
	}

	public String getIdentifiant_enseignant() {
		return identifiant_enseignant;
	}

	public void setIdentifiant_enseignant(String identifiant_enseignant) {
		this.identifiant_enseignant = identifiant_enseignant;
	}

	public String getPasse_enseignant() {
		return passe_enseignant;
	}

	public void setPasse_enseignant(String passe_enseignant) {
		this.passe_enseignant = passe_enseignant;
	}

	public String getNom_enseignant() {
		return nom_enseignant;
	}

	public void setNom_enseignant(String nom_enseignant) {
		this.nom_enseignant = nom_enseignant;
	}

	public String getPrenom_enseignant() {
		return prenom_enseignant;
	}

	public void setPrenom_enseignant(String prenom_enseignant) {
		this.prenom_enseignant = prenom_enseignant;
	}

	public String getEmail_enseignant() {
		return email_enseignant;
	}

	public void setEmail_enseignant(String email_enseignant) {
		this.email_enseignant = email_enseignant;
	}

	public String getAdresse_enseignant() {
		return adresse_enseignant;
	}

	public void setAdresse_enseignant(String adresse_enseignant) {
		this.adresse_enseignant = adresse_enseignant;
	}

	public String getTelephone_enseignant() {
		return telephone_enseignant;
	}

	public void setTelephone_enseignant(String telephone_enseignant) {
		this.telephone_enseignant = telephone_enseignant;
	}

	public String getTitre_enseignant() {
		return titre_enseignant;
	}

	public void setTitre_enseignant(String titre_enseignant) {
		this.titre_enseignant = titre_enseignant;
	}

	public String getDiscipline_enseignant() {
		return discipline_enseignant;
	}

	public void setDiscipline_enseignant(String discipline_enseignant) {
		this.discipline_enseignant = discipline_enseignant;
	}

	public String getGrade_enseignant() {
		return grade_enseignant;
	}

	public void setGrade_enseignant(String grade_enseignant) {
		this.grade_enseignant = grade_enseignant;
	}

}
