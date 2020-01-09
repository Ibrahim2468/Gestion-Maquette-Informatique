package com.sdzee.tp.dao;

import static com.sdzee.tp.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.sdzee.tp.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sdzee.tp.beans.Matiere;
import com.sdzee.tp.beans.Module;

public class MatiereDaoImpl implements MatiereDao {

	private static final String SQL_SELECT_ALL = "SELECT * FROM matiere ORDER BY id_matiere";
	private static final String SQL_SELECT_PAR_ID = "SELECT * FROM matiere WHERE id_matiere = ?";
	private static final String SQL_INSERT = "INSERT INTO matiere (intitule_matiere, volume_matiere, module) VALUES (?, ?, ?)";
	private static final String SQL_DELETE_PAR_ID = "DELETE FROM matiere WHERE id_matiere = ?";
	private static final String SQL_UPDATE = "UPDATE matiere SET intitule_matiere = ?, volume_matiere = ?, module = ? WHERE id_matiere = ?";
	private static final String SQL_SELECT_HEURE_INFINI = "SELECT * FROM matiere WHERE restant_matiere != 0";
	private static final String SQL_UPDATE_HEURES = "UPDATE matiere SET effectue_matiere = ?, restant_matiere = ? WHERE id_matiere = ?";

	private DAOFactory daoFactory;

	MatiereDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void creer(Matiere matiere) throws DAOException {
		// TODO Auto-generated method stub

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_INSERT, true, matiere.getIntitule_matiere(),
					matiere.getVolume_matiere(),
					(matiere.getModule()).getId_module());

			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {
				throw new DAOException(
						"Échec de la création de la metiere, aucune ligne ajoutée dans la table.");
			}

			valeursAutoGenerees = preparedStatement.getGeneratedKeys();

			if (valeursAutoGenerees.next()) {
				matiere.setId_matiere(valeursAutoGenerees.getLong(1));
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
	public Matiere trouver(long id, ModuleDao moduleDao,
			SemestreDao semestreDao, ClasseDao classeDao) throws DAOException {
		return trouver(moduleDao, semestreDao, classeDao, SQL_SELECT_PAR_ID, id);
	}

	private Matiere trouver(ModuleDao moduleDao, SemestreDao semestreDao,
			ClasseDao classeDao, String sql, Object... objets)
			throws DAOException {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		Matiere matiere = new Matiere();

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
				matiere = map(resultSet, moduleDao, semestreDao, classeDao);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return matiere;
	}

	@Override
	public List<Matiere> lister(ModuleDao moduleDao, SemestreDao semestreDao,
			ClasseDao classeDao) throws DAOException {
		// TODO Auto-generated method stub

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		List<Matiere> matieres = new ArrayList<Matiere>();

		try {

			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				matieres.add(map(resultSet, moduleDao, semestreDao, classeDao));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connection);
		}
		return matieres;
	}

	@Override
	public List<Matiere> listerRestant(ModuleDao moduleDao,
			SemestreDao semestreDao, ClasseDao classeDao) throws DAOException {
		// TODO Auto-generated method stub

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		List<Matiere> matieres = new ArrayList<Matiere>();

		try {

			connection = daoFactory.getConnection();
			preparedStatement = connection
					.prepareStatement(SQL_SELECT_HEURE_INFINI);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				matieres.add(map(resultSet, moduleDao, semestreDao, classeDao));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connection);
		}
		return matieres;
	}

	@Override
	public void supprimer(Matiere matiere) throws DAOException {
		// TODO Auto-generated method stub

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_DELETE_PAR_ID, true, matiere.getId_matiere());

			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				throw new DAOException(
						"Échec de la suppression du module, aucune ligne supprimée de la table.");
			} else {
				matiere.setId_matiere(null);
				// semestre.setId_semestre(null);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion);
		}

	}

	@Override
	public void update(Matiere matiere, String nom, int volume, int id_module)
			throws DAOException {
		// TODO Auto-generated method stub

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_UPDATE, true, nom, volume, id_module,
					matiere.getId_matiere());

			System.out.println("la requete est : " + preparedStatement);

			int statut = preparedStatement.executeUpdate();
			System.out.println("Update effectué");
			if (statut == 0) {
				throw new DAOException(
						"Échec de la modification de la metiere, aucune ligne modifiée de la table.");
			} else {
				// enseignant.setId(null);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

	}

	public void updateHeure(Matiere matiere, int effectue, int restant,
			int id_module) throws DAOException {
		// TODO Auto-generated method stub

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_UPDATE_HEURES, true, effectue, restant,
					matiere.getId_matiere());

			System.out.println("la requete est : " + preparedStatement);

			int statut = preparedStatement.executeUpdate();
			System.out.println("Update effectué");
			if (statut == 0) {
				throw new DAOException(
						"Échec de la modification de la metiere, aucune ligne modifiée de la table.");
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
	private static Matiere map(ResultSet resultSet, ModuleDao moduleDao,
			SemestreDao semestreDao, ClasseDao classeDao) throws SQLException {

		Matiere matiere = new Matiere();

		matiere.setId_matiere(resultSet.getLong("id_matiere"));
		matiere.setIntitule_matiere(resultSet.getString("intitule_matiere"));
		matiere.setVolume_matiere(resultSet.getInt("volume_matiere"));
		matiere.setRestant_matiere(resultSet.getInt("restant_matiere"));
		matiere.setEffectue_matiere(resultSet.getInt("Effectue_matiere"));

		Module module = moduleDao.trouver(resultSet.getInt("module"),
				semestreDao, classeDao);

		matiere.setModule(module);

		return matiere;
	}

}
