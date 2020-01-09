package com.sdzee.tp.dao;

import java.util.List;

import com.sdzee.tp.beans.Module;

public interface ModuleDao {

	void creer(Module module) throws DAOException;

	Module trouver(long id, SemestreDao semestreDAo, ClasseDao classeDao)
			throws DAOException;

	List<Module> lister(SemestreDao semestreDao, ClasseDao classeDao)
			throws DAOException;

	void supprimer(Module module) throws DAOException;

	void update(Module module, String nom, int id_semestre) throws DAOException;
}
