package com.sdzee.tp.dao;

import java.util.List;

import com.sdzee.tp.beans.Semestre;

public interface SemestreDao {

	void creer(Semestre semestre) throws DAOException;

	Semestre trouver(long id, ClasseDao classeDAo) throws DAOException;

	List<Semestre> lister(ClasseDao classeDao) throws DAOException;

	void supprimer(Semestre semestre) throws DAOException;

	void update(Semestre semestre, String nom, int id_classe)
			throws DAOException;
}