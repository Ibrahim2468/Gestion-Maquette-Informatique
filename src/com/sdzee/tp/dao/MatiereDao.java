package com.sdzee.tp.dao;

import java.util.List;

import com.sdzee.tp.beans.Matiere;

public interface MatiereDao {

	void creer(Matiere matiere) throws DAOException;

	Matiere trouver(long id, ModuleDao moduleDao, SemestreDao semestreDAo,
			ClasseDao classeDao) throws DAOException;

	List<Matiere> lister(ModuleDao moduleDao, SemestreDao semestreDao,
			ClasseDao classeDao) throws DAOException;

	void supprimer(Matiere matiere) throws DAOException;

	void update(Matiere matiere, String nom, int volume, int id_module)
			throws DAOException;

	void updateHeure(Matiere matiere, int effectue, int restant, int id_module)
			throws DAOException;

	List<Matiere> listerRestant(ModuleDao moduleDao, SemestreDao semestreDao,
			ClasseDao classeDao) throws DAOException;

}
