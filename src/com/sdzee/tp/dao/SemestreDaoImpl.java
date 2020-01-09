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
import com.sdzee.tp.beans.Semestre;

public class SemestreDaoImpl implements SemestreDao {

	private static final String SQL_SELECT_ALL = "SELECT * FROM semestre ORDER BY id_semestre";
	private static final String SQL_SELECT_PAR_ID = "SELECT * FROM semestre WHERE id_semestre = ?";
	private static final String SQL_INSERT = "INSERT INTO semestre (nom_semestre, classe) VALUES (?, ?)";
	private static final String SQL_DELETE_PAR_ID = "DELETE FROM semestre WHERE id_semestre = ?";
	private static final String SQL_UPDATE_PAR_ID = "UPDATE semestre SET nom_semestre = ?, classe = ? WHERE id_semestre = ?";

	private DAOFactory daoFactory;

	public SemestreDaoImpl(DAOFactory daoFactory) {
		// TODO Auto-generated constructor stub
		this.daoFactory = daoFactory;
	}

	@Override
	public void creer(Semestre semestre) throws DAOException {
		// TODO Auto-generated method stub

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_INSERT, true, semestre.getNom_semestre(),
					(semestre.getClasse()).getId_classe());

			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {
				throw new DAOException(
						"Échec de la création du semestre, aucune ligne ajoutée dans la table.");
			}

			valeursAutoGenerees = preparedStatement.getGeneratedKeys();

			if (valeursAutoGenerees.next()) {
				semestre.setId_semestre(valeursAutoGenerees.getLong(1));
			} else {
				throw new DAOException(
						"Échec de la création du semestre en base, aucun ID auto-généré retourné.");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(valeursAutoGenerees, preparedStatement,
					connexion);
		}

	}

	@Override
	public Semestre trouver(long id, ClasseDao classeDao) throws DAOException {
		return trouver(classeDao, SQL_SELECT_PAR_ID, id);
	}

	private Semestre trouver(ClasseDao classeDao, String sql, Object... objets)
			throws DAOException {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		Semestre semestre = new Semestre();

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
				semestre = map(resultSet, classeDao);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return semestre;
	}

	public List<Semestre> lister(ClasseDao classeDao) throws DAOException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		List<Semestre> semestres = new ArrayList<Semestre>();

		try {

			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				semestres.add(map(resultSet, classeDao));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connection);
		}
		return semestres;
	}

	@Override
	public void supprimer(Semestre semestre) throws DAOException {
		// TODO Auto-generated method stub

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_DELETE_PAR_ID, true, semestre.getId_semestre());

			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				throw new DAOException(
						"Échec de la suppression de la classe, aucune ligne supprimée de la table.");
			} else {
				semestre.setId_semestre(null);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion);
		}

	}

	@Override
	public void update(Semestre semestre, String nom, int id_classe)
			throws DAOException {
		// TODO Auto-generated method stub

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_UPDATE_PAR_ID, true, nom, id_classe,
					semestre.getId_semestre());

			System.out.println("la requete est : " + preparedStatement);

			int statut = preparedStatement.executeUpdate();
			System.out.println("Update effectué");
			if (statut == 0) {
				throw new DAOException(
						"Échec de la modification du semestre, aucune ligne modifiée de la table.");
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
	private static Semestre map(ResultSet resultSet, ClasseDao classeDao)
			throws SQLException {
		Semestre semestre = new Semestre();

		semestre.setId_semestre(resultSet.getLong("id_semestre"));
		semestre.setNom_semestre(resultSet.getString("nom_semestre"));

		// CreationClasseForm form = new CreationClasseForm(
		// new CreationClasse().getClasseDao());
		// Classe classe = form.trouverClasse(resultSet.getInt("classe"));

		Classe classe = classeDao.trouver(resultSet.getInt("classe"));

		semestre.setClasse(classe);

		return semestre;
	}
}
