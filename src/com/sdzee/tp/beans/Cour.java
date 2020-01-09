package com.sdzee.tp.beans;

public class Cour {

	private Long id;
	private String nom_cour;
	private Enseignant enseignant;
	private Salle salle;
	private Matiere matiere;
	private Classe classe;
	private String date_cour;
	private int duree_cour;
	private boolean valide_cour;
	private String detail_cour;

	public Cour() {
		// TODO Auto-generated constructor stub
	}

	public Cour(Long id, String nom_cour, Enseignant enseignant, Salle salle,
			Matiere matiere, Classe classe, String date_cour, int duree_cour,
			boolean valide_cour, String detail_cour) {
		super();
		this.id = id;
		this.nom_cour = nom_cour;
		this.enseignant = enseignant;
		this.salle = salle;
		this.matiere = matiere;
		this.classe = classe;
		this.date_cour = date_cour;
		this.duree_cour = duree_cour;
		this.valide_cour = valide_cour;
		this.detail_cour = detail_cour;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom_cour() {
		return nom_cour;
	}

	public void setNom_cour(String nom_cour) {
		this.nom_cour = nom_cour;
	}

	public Enseignant getEnseignant() {
		return enseignant;
	}

	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public String getDate_cour() {
		return date_cour;
	}

	public void setDate_cour(String date_cour) {
		this.date_cour = date_cour;
	}

	public int getDuree_cour() {
		return duree_cour;
	}

	public void setDuree_cour(int duree_cour) {
		this.duree_cour = duree_cour;
	}

	public boolean isValide_cour() {
		return valide_cour;
	}

	public void setValide_cour(boolean valide_cour) {
		this.valide_cour = valide_cour;
	}

	public String getDetail_cour() {
		return detail_cour;
	}

	public void setDetail_cour(String detail_cour) {
		this.detail_cour = detail_cour;
	}

}
