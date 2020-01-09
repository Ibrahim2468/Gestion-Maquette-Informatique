package com.sdzee.tp.beans;

public class Semestre {

	private Long id_semestre;
	private String nom_semestre;
	private Classe classe;

	public Semestre() {
		// TODO Auto-generated constructor stub
	}

	public Semestre(Long id_semestre, String nom_semestre, Classe classe) {
		super();
		this.id_semestre = id_semestre;
		this.nom_semestre = nom_semestre;
		this.classe = classe;
	}

	public Long getId_semestre() {
		return id_semestre;
	}

	public void setId_semestre(Long id_semestre) {
		this.id_semestre = id_semestre;
	}

	public String getNom_semestre() {
		return nom_semestre;
	}

	public void setNom_semestre(String nom_semestre) {
		this.nom_semestre = nom_semestre;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

}
