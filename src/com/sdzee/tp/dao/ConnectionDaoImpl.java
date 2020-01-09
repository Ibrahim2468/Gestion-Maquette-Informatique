package com.sdzee.tp.dao;

import static com.sdzee.tp.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.sdzee.tp.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sdzee.tp.beans.ChefDepartement;
import com.sdzee.tp.beans.Classe;
import com.sdzee.tp.beans.Enseignant;

public class ConnectionDaoImpl implements ConnectionDao {

	private static final String SQL_VERIF_CLASSE = "SELECT id_classe, nom_classe, identifiant_classe, passe_classe FROM classe WHERE identifiant_classe = ? AND passe_classe = ?";
	// private static final String SQL_SELECT_PAR_ID =
	// "SELECT id, nom, prenom, adresse, telephone, email, image FROM enseignant WHERE id = ?";
	private static final String SQL_VERIF_ENSEIGNANT = "SELECT 	id_enseignant, nom_enseignant, prenom_enseignant, adresse_enseignant, telephone_enseignant, email_enseignant, passe_enseignant, titre_enseignant, discipline_enseignant, grade_enseignant, identifiant_enseignant FROM enseignant WHERE identifiant_enseignant = ? AND passe_enseignant = ?";
	private static final String SQL_VERIF_CHEF = "SELECT * FROM chef_departement WHERE identifiant_chef = ? AND passe_chef = ?";
	// private static final String SQL_DELETE_PAR_ID =
	// "DELETE FROM enseignant WHERE id = ?";

	private DAOFactory daoFactory;

	ConnectionDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	/*
	 * Implémentation de la méthode définie dans l'interface enseignantDao
	 */
	@Override
	public Enseignant trouver_enseignant(String id, String passe)
			throws DAOException {
		return trouver_enseignant(SQL_VERIF_ENSEIGNANT, id, passe);
	}

	/*
	 * Implémentation de la méthode définie dans l'interface enseignantDao
	 */
	@Override
	public ChefDepartement trouver_chef(String id, String passe)
			throws DAOException {
		return trouver_chef(SQL_VERIF_CHEF, id, passe);
	}

	/*
	 * Implémentation de la méthode définie dans l'interface enseignantDao
	 */
	@Override
	public Classe trouver_classe(String id, String passe) throws DAOException {
		return trouver_classe(SQL_VERIF_CLASSE, id, passe);
	}

	/*
	 * Méthode générique utilisée pour retourner un enseignant depuis la base de
	 * données, correspondant à la requête SQL donnée prenant en paramètres les
	 * objets passés en argument.
	 */
	private Enseignant trouver_enseignant(String sql, String id, String passe)
			throws DAOException {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		Enseignant enseignant = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			/*
			 * Préparation de la requête avec les objets passés en arguments
			 * (ici, uniquement un id) et exécution.
			 */
			preparedStatement = initialisationRequetePreparee(connexion, sql,
					false, id, passe);
			resultSet = preparedStatement.executeQuery();
			/*
			 * Parcours de la ligne de données retournée dans le ResultSet
			 */
			if (resultSet.next()) {
				enseignant = map_enseignant(resultSet);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return enseignant;
	}

	/*
	 * Méthode générique utilisée pour retourner un enseignant depuis la base de
	 * données, correspondant à la requête SQL donnée prenant en paramètres les
	 * objets passés en argument.
	 */
	private Classe trouver_classe(String sql, String id, String passe)
			throws DAOException {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Classe classe = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			/*
			 * Préparation de la requête avec les objets passés en arguments
			 * (ici, uniquement un id) et exécution.
			 */
			preparedStatement = initialisationRequetePreparee(connexion, sql,
					false, id, passe);
			resultSet = preparedStatement.executeQuery();
			/*
			 * Parcours de la ligne de données retournée dans le ResultSet
			 */
			if (resultSet.next()) {
				classe = map_classe(resultSet);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return classe;
	}

	private ChefDepartement trouver_chef(String sql, String id, String passe) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ChefDepartement chef = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			/*
			 * Préparation de la requête avec les objets passés en arguments
			 * (ici, uniquement un id) et exécution.
			 */
			preparedStatement = initialisationRequetePreparee(connexion, sql,
					false, id, passe);
			resultSet = preparedStatement.executeQuery();
			/*
			 * Parcours de la ligne de données retournée dans le ResultSet
			 */
			if (resultSet.next()) {
				chef = map_chef(resultSet);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return chef;
	}

	/*
	 * Simple méthode utilitaire permettant de faire la correspondance (le
	 * mapping) entre une ligne issue de la table des enseignants (un ResultSet)
	 * et un bean enseignant.
	 */
	private static Enseignant map_enseignant(ResultSet resultSet)
			throws SQLException {

		Enseignant enseignant = new Enseignant();

		enseignant.setId(resultSet.getLong("id_enseignant"));
		enseignant.setNom_enseignant(resultSet.getString("nom_enseignant"));
		enseignant.setPrenom_enseignant(resultSet
				.getString("prenom_enseignant"));
		enseignant.setAdresse_enseignant(resultSet
				.getString("adresse_enseignant"));
		enseignant.setTelephone_enseignant(resultSet
				.getString("telephone_enseignant"));
		enseignant.setEmail_enseignant(resultSet.getString("email_enseignant"));
		enseignant.setPasse_enseignant(resultSet.getString("passe_enseignant"));
		enseignant.setTitre_enseignant(resultSet.getString("titre_enseignant"));
		enseignant.setDiscipline_enseignant(resultSet
				.getString("discipline_enseignant"));
		enseignant.setGrade_enseignant(resultSet.getString("grade_enseignant"));
		enseignant.setIdentifiant_enseignant(resultSet
				.getString("identifiant_enseignant"));

		return enseignant;
	}

	private static Classe map_classe(ResultSet resultSet) throws SQLException {
		Classe classe = new Classe();

		classe.setId_classe(resultSet.getLong("id_classe"));
		classe.setNom_classe(resultSet.getString("nom_classe"));
		classe.setIdentifiant_classe(resultSet.getString("identifiant_classe"));
		classe.setPasse_classe(resultSet.getString("passe_classe"));

		return classe;
	}

	private static ChefDepartement map_chef(ResultSet resultSet)
			throws SQLException {
		ChefDepartement chef = new ChefDepartement();

		chef.setId_chef(resultSet.getLong("id_chef"));
		chef.setNom_chef(resultSet.getString("nom_chef"));
		chef.setPrenom_chef(resultSet.getString("prenom_chef"));
		chef.setIdentifiant_chef(resultSet.getString("identifiant_chef"));
		chef.setPasse_chef(resultSet.getString("passe_chef"));
		chef.setEmail_chef(resultSet.getString("email_chef"));

		return chef;
	}

}