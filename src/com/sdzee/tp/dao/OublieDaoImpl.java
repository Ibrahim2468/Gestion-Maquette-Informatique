package com.sdzee.tp.dao;

import static com.sdzee.tp.dao.DAOUtilitaire.fermeturesSilencieuses;
import static com.sdzee.tp.dao.DAOUtilitaire.initialisationRequetePreparee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sdzee.tp.beans.ChefDepartement;
import com.sdzee.tp.beans.Classe;
import com.sdzee.tp.beans.Enseignant;

public class OublieDaoImpl implements OublieDao {

	private static final String SQL_SELECT_CHEF = "SELECT * FROM chef_departement WHERE email_chef = ?";
	private static final String SQL_SELECT_ENSEIGNANT = "SELECT * FROM enseignant WHERE email_enseignant = ?";
	private static final String SQL_SELECT_CLASSE = "SELECT * FROM classe WHERE email_classe = ?";

	private DAOFactory daoFactory;

	OublieDaoImpl(DAOFactory daofactory) {
		// TODO Auto-generated constructor stub
		this.daoFactory = daofactory;
	}

	@Override
	public List<Enseignant> trouverEnseignant(String email) throws DAOException {
		return trouverEnseignant(SQL_SELECT_ENSEIGNANT, email);
	}

	private List<Enseignant> trouverEnseignant(String sql, Object... objets)
			throws DAOException {
		// TODO Auto-generated method stub

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		List<Enseignant> enseignants = new ArrayList<Enseignant>();
		try {
			connection = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connection, sql,
					false, objets);

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				enseignants.add(map_enseignant(resultSet));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connection);
		}
		return enseignants;
	}

	@Override
	public List<ChefDepartement> trouverChefDepartement(String email)
			throws DAOException {
		return trouverChefDepartement(SQL_SELECT_CHEF, email);
	}

	private List<ChefDepartement> trouverChefDepartement(String sql,
			Object... objets) throws DAOException {
		// TODO Auto-generated method stub

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		List<ChefDepartement> chefs = new ArrayList<ChefDepartement>();
		try {
			connection = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connection, sql,
					false, objets);

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				chefs.add(map_chef_departement(resultSet));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connection);
		}
		return chefs;
	}

	@Override
	public List<Classe> trouverClasse(String email) throws DAOException {
		return trouverClasse(SQL_SELECT_CLASSE, email);
	}

	private List<Classe> trouverClasse(String sql, Object... objets)
			throws DAOException {
		// TODO Auto-generated method stub

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		List<Classe> classes = new ArrayList<Classe>();
		try {
			connection = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connection, sql,
					false, objets);

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				classes.add(map_classe(resultSet));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connection);
		}
		return classes;
	}

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

	private static ChefDepartement map_chef_departement(ResultSet resulset)
			throws SQLException {

		ChefDepartement chef = new ChefDepartement();

		chef.setId_chef(resulset.getLong("id_chef"));
		chef.setNom_chef(resulset.getString("nom_chef"));
		chef.setPrenom_chef(resulset.getString("prenom_chef"));
		chef.setEmail_chef(resulset.getString("email_chef"));
		chef.setPasse_chef(resulset.getString("passe_chef"));
		chef.setIdentifiant_chef(resulset.getString("identifiant_chef"));

		return chef;
	}

	private static Classe map_classe(ResultSet resultSet) throws SQLException {

		Classe classe = new Classe();

		classe.setId_classe(resultSet.getLong("id_classe"));
		classe.setNom_classe(resultSet.getString("nom_classe"));
		classe.setIdentifiant_classe(resultSet.getString("identifiant_classe"));
		classe.setPasse_classe(resultSet.getString("passe_classe"));
		classe.setEmail_classe(resultSet.getString("email_classe"));

		return classe;
	}

}
