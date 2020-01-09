package com.sdzee.tp.dao;

import static com.sdzee.tp.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.sdzee.tp.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sdzee.tp.beans.Salle;

public class SalleDaoImpl implements SalleDao {

	private static final String SQL_SELECT_ALL = "SELECT * FROM salle ORDER BY id_salle";
	private static final String SQL_SELECT_PAR_ID = "SELECT * FROM salle WHERE id_salle = ?";
	private static final String SQL_INSERT = "INSERT INTO salle (nom_salle) VALUES (?)";
	private static final String SQL_DELETE_PAR_ID = "DELETE FROM salle WHERE id_salle = ?";
	private static final String SQL_UPDATE_PAR_ID = "UPDATE salle SET nom_salle = ? WHERE id_salle = ?";

	private DAOFactory daoFactory;

	public SalleDaoImpl(DAOFactory daoFactory) {
		// TODO Auto-generated constructor stub
		this.daoFactory = daoFactory;
	}

	@Override
	public void creer(Salle salle) throws DAOException {
		// TODO Auto-generated method stub

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_INSERT, true, salle.getNom_salle());

			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {
				throw new DAOException(
						"Échec de la création de la salle, aucune ligne ajoutée dans la table.");
			}

			valeursAutoGenerees = preparedStatement.getGeneratedKeys();

			if (valeursAutoGenerees.next()) {
				salle.setId_salle(valeursAutoGenerees.getLong(1));
			} else {
				throw new DAOException(
						"Échec de la création de la salle en base, aucun ID auto-généré retourné.");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(valeursAutoGenerees, preparedStatement,
					connexion);
		}

	}

	@Override
	public Salle trouver(long id) throws DAOException {
		// TODO Auto-generated method stub
		return trouver(SQL_SELECT_PAR_ID, id);
	}

	private Salle trouver(String sql, Object... objets) throws DAOException {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		Salle salle = new Salle();

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
				salle = map(resultSet);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return salle;
	}

	@Override
	public List<Salle> lister() throws DAOException {
		// TODO Auto-generated method stub

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		List<Salle> salles = new ArrayList<Salle>();

		try {

			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				salles.add(map(resultSet));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connection);
		}
		return salles;
	}

	@Override
	public void supprimer(Salle salle) throws DAOException {
		// TODO Auto-generated method stub

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_DELETE_PAR_ID, true, salle.getId_salle());

			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				throw new DAOException(
						"Échec de la suppression de la classe, aucune ligne supprimée de la table.");
			} else {
				salle.setId_salle(null);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion);
		}

	}

	@Override
	public void update(Salle salle, String nom) throws DAOException {
		// TODO Auto-generated method stub

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_UPDATE_PAR_ID, true, nom, salle.getId_salle());

			System.out.println("la requete est : " + preparedStatement);

			int statut = preparedStatement.executeUpdate();
			System.out.println("Update effectué");
			if (statut == 0) {
				throw new DAOException(
						"Échec de la modification de la salle, aucune ligne supprimée de la table.");
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
	private static Salle map(ResultSet resultSet) throws SQLException {
		Salle salle = new Salle();

		salle.setId_salle(resultSet.getLong("id_salle"));
		salle.setNom_salle(resultSet.getString("nom_salle"));

		return salle;
	}

}
