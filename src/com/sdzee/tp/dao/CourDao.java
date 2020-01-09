package com.sdzee.tp.dao;

import java.util.List;

import com.sdzee.tp.beans.Cour;

public interface CourDao {

	void creer(Cour cour) throws DAOException;

	Cour trouver(long id, EnseignantDao enseignantDao, MatiereDao matiereDao,
			SalleDao salleDao, ModuleDao moduleDao, SemestreDao semestreDAo,
			ClasseDao classeDao) throws DAOException;

	List<Cour> lister(EnseignantDao enseignantDao, MatiereDao matiereDao,
			SalleDao salleDao, ModuleDao moduleDao, SemestreDao semestreDAo,
			ClasseDao classeDao) throws DAOException;

	void supprimer(Cour cour) throws DAOException;

	void update(Cour cour, String nom, int id_enseignant, int id_salle,
			int id_matiere, int id_classe, String date, String duree)
			throws DAOException;

	List<Cour> listerCourenseignant(EnseignantDao enseignantDao,
			MatiereDao matiereDao, SalleDao salleDao, ModuleDao moduleDao,
			SemestreDao semestreDAo, ClasseDao classeDao, int id_enseignant)
			throws DAOException;

}
