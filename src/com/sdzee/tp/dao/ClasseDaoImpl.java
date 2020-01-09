package com.sdzee.tp.dao;

import static com.sdzee.tp.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.sdzee.tp.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sdzee.tp.beans.Classe;

public class ClasseDaoImpl implements ClasseDao {

	private static final String SQL_SELECT_ALL = "SELECT id_classe, nom_classe, identifiant_classe, passe_classe, email_classe FROM classe ORDER BY id_classe";
	private static final String SQL_SELECT_PAR_ID = "SELECT * FROM classe WHERE id_classe = ?";
	private static final String SQL_INSERT = "INSERT INTO classe (nom_classe, identifiant_classe, passe_classe, email_classe) VALUES (?, ?, ?, ?)";
	private static final String SQL_DELETE_PAR_ID = "DELETE FROM classe WHERE id_classe = ?";
	private static final String SQL_UPDATE_PAR_ID = "UPDATE classe SET nom_classe = ?, email_classe = ? WHERE id_classe = ?";

	private DAOFactory daoFactory;

	public ClasseDaoImpl(DAOFactory daoFactory) {
		// TODO Auto-generated constructor stub
		this.daoFactory = daoFactory;
	}

	@Override
	public void creer(Classe classe) throws DAOException {
		// TODO Auto-generated method stub

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_INSERT, true, classe.getNom_classe(),
					classe.getIdentifiant_classe(), classe.getPasse_classe(),
					classe.getEmail_classe());

			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {
				throw new DAOException(
						"Échec de la création de la classe, aucune ligne ajoutée dans la table.");
			}

			valeursAutoGenerees = preparedStatement.getGeneratedKeys();

			if (valeursAutoGenerees.next()) {
				classe.setId_classe(valeursAutoGenerees.getLong(1));
			} else {
				throw new DAOException(
						"Échec de la création de la classe en base, aucun ID auto-généré retourné.");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(valeursAutoGenerees, preparedStatement,
					connexion);
		}

	}

	@Override
	public Classe trouver(long id) throws DAOException {
		return trouver(SQL_SELECT_PAR_ID, id);
	}

	private Classe trouver(String sql, Object... objets) throws DAOException {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		Classe classe = new Classe();

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
				classe = map(resultSet);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return classe;
	}

	public List<Classe> lister() throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		List<Classe> classes = new ArrayList<Classe>();

		try {

			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				classes.add(map(resultSet));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connection);
		}
		return classes;
	}

	@Override
	public void supprimer(Classe classe) throws DAOException {
		// TODO Auto-generated method stub

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_DELETE_PAR_ID, true, classe.getId_classe());

			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				throw new DAOException(
						"Échec de la suppression de la classe, aucune ligne supprimée de la table.");
			} else {
				classe.setId_classe(null);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion);
		}

	}

	@Override
	public void update(Classe classe, String nom, String email)
			throws DAOException {
		// TODO Auto-generated method stub

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_UPDATE_PAR_ID, true, nom, email, classe.getId_classe());

			System.out.println("la requete est : " + preparedStatement);

			int statut = preparedStatement.executeUpdate();
			System.out.println("Update effectué");
			if (statut == 0) {
				throw new DAOException(
						"Échec de la modification de la classe, aucune ligne supprimée de la table.");
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
	private static Classe map(ResultSet resultSet) throws SQLException {
		Classe classe = new Classe();

		classe.setId_classe(resultSet.getLong("id_classe"));
		classe.setNom_classe(resultSet.getString("nom_classe"));
		classe.setIdentifiant_classe(resultSet.getString("identifiant_classe"));
		classe.setPasse_classe(resultSet.getString("passe_classe"));
		classe.setEmail_classe(resultSet.getString("email_classe"));

		return classe;
	}
}
