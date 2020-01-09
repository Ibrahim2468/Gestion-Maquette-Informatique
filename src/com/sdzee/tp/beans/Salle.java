package com.sdzee.tp.beans;

public class Salle {

	private Long id_salle;
	private String nom_salle;

	public Salle() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((id_salle == null) ? 0 : id_salle.hashCode());
		result = prime * result
				+ ((nom_salle == null) ? 0 : nom_salle.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Salle other = (Salle) obj;
		if (id_salle == null) {
			if (other.id_salle != null)
				return false;
		} else if (!id_salle.equals(other.id_salle))
			return false;
		if (nom_salle == null) {
			if (other.nom_salle != null)
				return false;
		} else if (!nom_salle.equals(other.nom_salle))
			return false;
		return true;
	}

	public Long getId_salle() {
		return id_salle;
	}

	public void setId_salle(Long id_salle) {
		this.id_salle = id_salle;
	}

	public String getNom_salle() {
		return nom_salle;
	}

	public void setNom_salle(String nom_salle) {
		this.nom_salle = nom_salle;
	}

}
