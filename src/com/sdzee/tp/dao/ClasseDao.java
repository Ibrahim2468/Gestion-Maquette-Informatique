package com.sdzee.tp.dao;

import java.util.List;

import com.sdzee.tp.beans.Classe;

public interface ClasseDao {
	void creer(Classe classe) throws DAOException;

	Classe trouver(long id) throws DAOException;

	List<Classe> lister() throws DAOException;

	void supprimer(Classe classe) throws DAOException;

	void update(Classe classe, String nom, String email) throws DAOException;
}