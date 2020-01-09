package com.sdzee.tp.beans;

public class Module {

	private Long id_module;
	private String nom_module;
	private Semestre semestre;

	public Module() {
		// TODO Auto-generated constructor stub
	}

	public Module(Long id_module, String nom_module, Semestre semestre) {
		super();
		this.id_module = id_module;
		this.nom_module = nom_module;
		this.semestre = semestre;
	}

	public Long getId_module() {
		return id_module;
	}

	public void setId_module(Long id_module) {
		this.id_module = id_module;
	}

	public String getNom_module() {
		return nom_module;
	}

	public void setNom_module(String nom_module) {
		this.nom_module = nom_module;
	}

	public Semestre getSemestre() {
		return semestre;
	}

	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}

}
