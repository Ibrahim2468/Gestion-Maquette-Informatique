package com.sdzee.tp.dao;

import static com.sdzee.tp.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.sdzee.tp.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sdzee.tp.beans.Enseignant;

public class EnseignantDaoImpl implements EnseignantDao {

	private static final String SQL_SELECT_ALL = "SELECT * FROM enseignant ORDER BY id_enseignant";
	private static final String SQL_SELECT = "SELECT id, nom, prenom, adresse, telephone, email, image FROM enseignant ORDER BY id";
	private static final String SQL_SELECT_PAR_ID = "SELECT * FROM enseignant WHERE id_enseignant = ?";
	private static final String SQL_INSERT = "INSERT INTO enseignant (nom_enseignant, prenom_enseignant, adresse_enseignant, telephone_enseignant, email_enseignant, passe_enseignant, titre_enseignant, discipline_enseignant, grade_enseignant, identifiant_enseignant) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_DELETE_PAR_ID = "DELETE FROM enseignant WHERE id_enseignant = ?";
	private static final String SQL_UPDATE = "UPDATE enseignant SET nom_enseignant = ?, prenom_enseignant = ?, adresse_enseignant = ?, telephone_enseignant = ?, email_enseignant = ?, titre_enseignant = ?, discipline_enseignant = ?, grade_enseignant = ? WHERE id_enseignant = ?";

	private DAOFactory daoFactory;

	EnseignantDaoImpl(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	/*
	 * Implémentation de la méthode définie dans l'interface enseignantDao
	 */
	@Override
	public Enseignant trouver(long id) throws DAOException {
		return trouver(SQL_SELECT_PAR_ID, id);
	}

	/*
	 * Implémentation de la méthode définie dans l'interface enseignantDao
	 */
	@Override
	public void creer(Enseignant enseignant) throws DAOException {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {
			connexion = daoFactory.getConnection();

			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_INSERT, true, enseignant.getNom_enseignant(),
					enseignant.getPrenom_enseignant(),
					enseignant.getAdresse_enseignant(),
					enseignant.getTelephone_enseignant(),
					enseignant.getEmail_enseignant(),
					enseignant.getPasse_enseignant(),
					enseignant.getTitre_enseignant(),
					enseignant.getDiscipline_enseignant(),
					enseignant.getGrade_enseignant(),
					enseignant.getIdentifiant_enseignant());

			System.out.println("DEDANS");

			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {
				throw new DAOException(
						"Échec de la création du enseignant, aucune ligne ajoutée dans la table.");
			}

			valeursAutoGenerees = preparedStatement.getGeneratedKeys();

			if (valeursAutoGenerees.next()) {
				enseignant.setId(valeursAutoGenerees.getLong(1));
				System.out.println("Excecution de la requete.");
			} else {
				throw new DAOException(
						"Échec de la création du enseignant en base, aucun ID auto-généré retourné.");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(valeursAutoGenerees, preparedStatement,
					connexion);
		}
	}

	/*
	 * Implémentation de la méthode définie dans l'interface enseignantDao
	 */
	@Override
	public List<Enseignant> lister() throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Enseignant> enseignants = new ArrayList<Enseignant>();
		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement(SQL_SELECT);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				enseignants.add(map(resultSet));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connection);
		}
		return enseignants;
	}

	/*
	 * Implémentation de la méthode définie dans l'interface enseignantDao
	 */
	@Override
	public void supprimer(Enseignant enseignant) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_DELETE_PAR_ID, true, enseignant.getId());
			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				throw new DAOException(
						"Échec de la suppression du enseignant, aucune ligne supprimée de la table.");
			} else {
				// enseignant.setId(null);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion);
		}
	}

	/*
	 * Méthode générique utilisée pour retourner un enseignant depuis la base de
	 * données, correspondant à la requête SQL donnée prenant en paramètres les
	 * objets passés en argument.
	 */
	private Enseignant trouver(String sql, Object... objets)
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
					false, objets);
			resultSet = preparedStatement.executeQuery();
			/*
			 * Parcours de la ligne de données retournée dans le ResultSet
			 */
			if (resultSet.next()) {
				enseignant = map(resultSet);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return enseignant;
	}

	/*
	 * Simple méthode utilitaire permettant de faire la correspondance (le
	 * mapping) entre une ligne issue de la table des enseignants (un ResultSet)
	 * et un bean enseignant.
	 */
	private static Enseignant map(ResultSet resultSet) throws SQLException {
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

	@Override
	public List<Enseignant> lister_tout() throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Enseignant> enseignants = new ArrayList<Enseignant>();

		try {
			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
			resultSet = preparedStatement.executeQuery();
			int i = 0;
			while (resultSet.next()) {
				enseignants.add(map(resultSet));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connection);
		}
		return enseignants;
	}

	/*
	 * Implémentation de la méthode définie dans l'interface enseignantDao
	 */
	@Override
	public void supprimer_par_id(int id) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_DELETE_PAR_ID, true, id);
			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				throw new DAOException(
						"Échec de la suppression du enseignant, aucune ligne supprimée de la table.");
			} else {
				// enseignant.setId(null);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion);
		}
	}

	@Override
	public void modifier(int id, Enseignant enseignant) throws DAOException {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_UPDATE, true, enseignant.getNom_enseignant(),
					enseignant.getPrenom_enseignant(),
					enseignant.getAdresse_enseignant(),
					enseignant.getTelephone_enseignant(),
					enseignant.getEmail_enseignant(),
					enseignant.getTitre_enseignant(),
					enseignant.getDiscipline_enseignant(),
					enseignant.getGrade_enseignant(), id);

			System.out.println("la requete est : " + preparedStatement);

			int statut = preparedStatement.executeUpdate();
			System.out.println("Update effectué");
			if (statut == 0) {
				throw new DAOException(
						"Échec de la modification du enseignant, aucune ligne supprimée de la table.");
			} else {
				// enseignant.setId(null);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}

	}
}