package com.sdzee.tp.dao;

import java.util.List;

import com.sdzee.tp.beans.ChefDepartement;
import com.sdzee.tp.beans.Classe;
import com.sdzee.tp.beans.Enseignant;

public interface OublieDao {

	List<Enseignant> trouverEnseignant(String email) throws DAOException;

	List<ChefDepartement> trouverChefDepartement(String email)
			throws DAOException;

	List<Classe> trouverClasse(String email) throws DAOException;
}
