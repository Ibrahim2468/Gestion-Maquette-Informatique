package com.sdzee.tp.beans;

public class ChefDepartement {

	private Long id_chef;
	private String nom_chef;
	private String prenom_chef;
	private String email_chef;
	private String identifiant_chef;
	private String passe_chef;

	public ChefDepartement() {
		// TODO Auto-generated constructor stub
	}

	public ChefDepartement(Long id_chef, String nom_chef, String prenom_chef,
			String email_chef, String identifiant_chef, String passe_chef) {
		super();
		this.id_chef = id_chef;
		this.nom_chef = nom_chef;
		this.prenom_chef = prenom_chef;
		this.email_chef = email_chef;
		this.identifiant_chef = identifiant_chef;
		this.passe_chef = passe_chef;
	}

	public Long getId_chef() {
		return id_chef;
	}

	public void setId_chef(Long id_chef) {
		this.id_chef = id_chef;
	}

	public String getNom_chef() {
		return nom_chef;
	}

	public void setNom_chef(String nom_chef) {
		this.nom_chef = nom_chef;
	}

	public String getPrenom_chef() {
		return prenom_chef;
	}

	public void setPrenom_chef(String prenom_chef) {
		this.prenom_chef = prenom_chef;
	}

	public String getEmail_chef() {
		return email_chef;
	}

	public void setEmail_chef(String email_chef) {
		this.email_chef = email_chef;
	}

	public String getIdentifiant_chef() {
		return identifiant_chef;
	}

	public void setIdentifiant_chef(String identifiant_chef) {
		this.identifiant_chef = identifiant_chef;
	}

	public String getPasse_chef() {
		return passe_chef;
	}

	public void setPasse_chef(String passe_chef) {
		this.passe_chef = passe_chef;
	}

}
