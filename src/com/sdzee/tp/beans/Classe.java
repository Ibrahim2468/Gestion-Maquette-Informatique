package com.sdzee.tp.beans;

public class Classe {

	private Long id_classe;
	private String nom_classe;
	private String identifiant_classe;
	private String passe_classe;
	private String email_classe;

	public Classe() {
		// TODO Auto-generated constructor stub
	}

	public Classe(Long id_classe, String nom_classe, String identifiant_classe,
			String passe_classe) {
		super();
		this.id_classe = id_classe;
		this.nom_classe = nom_classe;
		this.identifiant_classe = identifiant_classe;
		this.passe_classe = passe_classe;
	}

	public Long getId_classe() {
		return id_classe;
	}

	public void setId_classe(Long id_classe) {
		this.id_classe = id_classe;
	}

	public String getNom_classe() {
		return nom_classe;
	}

	public void setNom_classe(String nom_classe) {
		this.nom_classe = nom_classe;
	}

	public String getIdentifiant_classe() {
		return identifiant_classe;
	}

	public void setIdentifiant_classe(String identifiant_classe) {
		this.identifiant_classe = identifiant_classe;
	}

	public String getPasse_classe() {
		return passe_classe;
	}

	public void setPasse_classe(String passe_classe) {
		this.passe_classe = passe_classe;
	}

	public String getEmail_classe() {
		return email_classe;
	}

	public void setEmail_classe(String email_classe) {
		this.email_classe = email_classe;
	}

}
