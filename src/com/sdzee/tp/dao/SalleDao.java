package com.sdzee.tp.dao;

import java.util.List;

import com.sdzee.tp.beans.Salle;

public interface SalleDao {

	void creer(Salle salle) throws DAOException;

	Salle trouver(long id) throws DAOException;

	List<Salle> lister() throws DAOException;

	void supprimer(Salle salle) throws DAOException;

	void update(Salle salle, String nom) throws DAOException;

}
