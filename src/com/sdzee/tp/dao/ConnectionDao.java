package com.sdzee.tp.dao;

import com.sdzee.tp.beans.ChefDepartement;
import com.sdzee.tp.beans.Classe;
import com.sdzee.tp.beans.Enseignant;

public interface ConnectionDao {

	Enseignant trouver_enseignant(String id, String passe) throws DAOException;

	Classe trouver_classe(String id, String passe) throws DAOException;

	ChefDepartement trouver_chef(String id, String passe) throws DAOException;

}