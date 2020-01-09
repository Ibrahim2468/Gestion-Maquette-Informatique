package com.sdzee.tp.beans;

public class Matiere {

	private Long id_matiere;
	private String intitule_matiere;
	private int volume_matiere;
	private int effectue_matiere;
	private int restant_matiere;
	private Module module;

	public Matiere() {
		// TODO Auto-generated constructor stub
	}

	public Matiere(Long id_matiere, String intitule_matiere,
			int volume_matiere, int effectue_matiere, int restant_matiere,
			Module module) {
		super();
		this.id_matiere = id_matiere;
		this.intitule_matiere = intitule_matiere;
		this.volume_matiere = volume_matiere;
		this.effectue_matiere = effectue_matiere;
		this.restant_matiere = restant_matiere;
		this.module = module;
	}

	public Long getId_matiere() {
		return id_matiere;
	}

	public void setId_matiere(Long id_matiere) {
		this.id_matiere = id_matiere;
	}

	public String getIntitule_matiere() {
		return intitule_matiere;
	}

	public void setIntitule_matiere(String intitule_matiere) {
		this.intitule_matiere = intitule_matiere;
	}

	public int getVolume_matiere() {
		return volume_matiere;
	}

	public void setVolume_matiere(int volume_matiere) {
		this.volume_matiere = volume_matiere;
	}

	public int getEffectue_matiere() {
		return effectue_matiere;
	}

	public void setEffectue_matiere(int effectue_matiere) {
		this.effectue_matiere = effectue_matiere;
	}

	public int getRestant_matiere() {
		return restant_matiere;
	}

	public void setRestant_matiere(int restant_matiere) {
		this.restant_matiere = restant_matiere;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

}
