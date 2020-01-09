package com.sdzee.tp.dao;

import java.util.List;

import com.sdzee.tp.beans.Enseignant;

public interface EnseignantDao {
	void creer(Enseignant enseignant) throws DAOException;

	Enseignant trouver(long id) throws DAOException;

	List<Enseignant> lister() throws DAOException;

	List<Enseignant> lister_tout() throws DAOException;

	void supprimer(Enseignant enseignant) throws DAOException;

	void supprimer_par_id(int id) throws DAOException;

	void modifier(int id, Enseignant enseignant) throws DAOException;
}