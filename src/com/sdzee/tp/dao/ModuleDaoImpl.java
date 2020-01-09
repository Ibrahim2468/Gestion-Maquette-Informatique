package com.sdzee.tp.dao;

import static com.sdzee.tp.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.sdzee.tp.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sdzee.tp.beans.Module;
import com.sdzee.tp.beans.Semestre;

public class ModuleDaoImpl implements ModuleDao {

	private static final String SQL_SELECT_ALL = "SELECT * FROM module ORDER BY id_module";
	private static final String SQL_SELECT_PAR_ID = "SELECT * FROM module WHERE id_module = ?";
	private static final String SQL_INSERT = "INSERT INTO module (nom_module, semestre) VALUES (?, ?)";
	private static final String SQL_DELETE_PAR_ID = "DELETE FROM module WHERE id_module = ?";
	private static final String SQL_UPDATE_PAR_ID = "UPDATE module SET nom_module = ?, semestre = ? WHERE id_module = ?";

	private DAOFactory daoFactory;

	public ModuleDaoImpl(DAOFactory daoFactory) {
		// TODO Auto-generated constructor stub
		this.daoFactory = daoFactory;
	}

	@Override
	public void creer(Module module) throws DAOException {
		// TODO Auto-generated method stub

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_INSERT, true, module.getNom_module(),
					(module.getSemestre()).getId_semestre());

			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {
				throw new DAOException(
						"Échec de la création du module, aucune ligne ajoutée dans la table.");
			}

			valeursAutoGenerees = preparedStatement.getGeneratedKeys();

			if (valeursAutoGenerees.next()) {
				module.setId_module(valeursAutoGenerees.getLong(1));
			} else {
				throw new DAOException(
						"Échec de la création du module en base, aucun ID auto-généré retourné.");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(valeursAutoGenerees, preparedStatement,
					connexion);
		}

	}

	@Override
	public Module trouver(long id, SemestreDao semestreDao, ClasseDao classeDao)
			throws DAOException {
		return trouver(semestreDao, classeDao, SQL_SELECT_PAR_ID, id);
	}

	private Module trouver(SemestreDao semestreDao, ClasseDao classeDao,
			String sql, Object... objets) throws DAOException {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		Module module = new Module();

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			/*
			 * Préparation de la requête avec les objets passés en arguments
			 * (ici, uniquement un id) et exécution.
			 */
			preparedStatement = initialisationRequetePreparee(connexion, sql,
					false, objets);
			resultSet = preparedStatement.executeQuery();

			/*
			 * Parcours de la ligne de données retournée dans le ResultSet
			 */
			if (resultSet.next()) {
				module = map(resultSet, semestreDao, classeDao);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return module;
	}

	public List<Module> lister(SemestreDao semestreDao, ClasseDao classeDao)
			throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		List<Module> modules = new ArrayList<Module>();

		try {

			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				modules.add(map(resultSet, semestreDao, classeDao));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connection);
		}
		return modules;
	}

	@Override
	public void supprimer(Module module) throws DAOException {
		// TODO Auto-generated method stub

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_DELETE_PAR_ID, true, module.getId_module());

			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				throw new DAOException(
						"Échec de la suppression du module, aucune ligne supprimée de la table.");
			} else {
				module.setId_module(null);
				// semestre.setId_semestre(null);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion);
		}

	}

	@Override
	public void update(Module module, String nom, int id_semestre)
			throws DAOException {
		// TODO Auto-generated method stub

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_UPDATE_PAR_ID, true, nom, id_semestre,
					module.getId_module());

			System.out.println("la requete est : " + preparedStatement);

			int statut = preparedStatement.executeUpdate();
			System.out.println("Update effectué");
			if (statut == 0) {
				throw new DAOException(
						"Échec de la modification du module, aucune ligne modifiée de la table.");
			} else {
				// enseignant.setId(null);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

	}

	/*
	 * Simple méthode utilitaire permettant de faire la correspondance (le
	 * mapping) entre une ligne issue de la table des clients (un ResultSet) et
	 * un bean Client.
	 */
	private static Module map(ResultSet resultSet, SemestreDao semestreDao,
			ClasseDao classeDao) throws SQLException {
		Module module = new Module();

		// semestre.setId_semestre(resultSet.getLong("id_semestre"));
		// semestre.setNom_semestre(resultSet.getString("nom_semestre"));

		module.setId_module(resultSet.getLong("id_module"));
		module.setNom_module(resultSet.getString("nom_module"));

		Semestre semestre = semestreDao.trouver(resultSet.getInt("semestre"),
				classeDao);
		module.setSemestre(semestre);

		// Classe classe = classeDao.trouver(resultSet.getInt("classe"));

		// semestre.setClasse(classe);module.setSemestre(semestre);

		return module;
	}
}
