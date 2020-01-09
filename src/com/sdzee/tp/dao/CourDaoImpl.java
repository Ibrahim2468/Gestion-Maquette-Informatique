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
import com.sdzee.tp.beans.Cour;
import com.sdzee.tp.beans.Enseignant;
import com.sdzee.tp.beans.Matiere;
import com.sdzee.tp.beans.Salle;

public class CourDaoImpl implements CourDao {

	private static final String SQL_SELECT_ALL = "SELECT * FROM cour ORDER BY id_cour";
	private static final String SQL_SELECT_PAR_ID = "SELECT * FROM cour WHERE id_cour = ?";
	private static final String SQL_SELECT_COUR_ENSEIGNANT = "SELECT * FROM cour WHERE enseignant = ?";
	private static final String SQL_INSERT = "INSERT INTO cour (nom_cour, enseignant, salle, matiere, classe, date, duree, valide, details_cour) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_DELETE_PAR_ID = "DELETE FROM cour WHERE id_cour = ?";
	private static final String SQL_UPDATE = "UPDATE cour SET nom_cour = ?, enseignant = ?, salle = ?, matiere = ?, classe = ?, date = ?, duree = ? WHERE id_cour = ?";

	private DAOFactory daoFactory;

	CourDaoImpl(DAOFactory daoFactory) {
		// TODO Auto-generated constructor stub
		this.daoFactory = daoFactory;
	}

	@Override
	public void creer(Cour cour) throws DAOException {
		// TODO Auto-generated method stub

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_INSERT, true, cour.getNom_cour(), cour.getEnseignant()
							.getId(), cour.getSalle().getId_salle(), cour
							.getMatiere().getId_matiere(), cour.getClasse()
							.getId_classe(), cour.getDate_cour(),
					cour.getDuree_cour(), 0, cour.getDetail_cour());

			int statut = preparedStatement.executeUpdate();

			if (statut == 0) {
				throw new DAOException(
						"Échec de la création du cour, aucune ligne ajoutée dans la table.");
			}

			valeursAutoGenerees = preparedStatement.getGeneratedKeys();

			if (valeursAutoGenerees.next()) {
				cour.setId(valeursAutoGenerees.getLong(1));
			} else {
				throw new DAOException(
						"Échec de la création du cour en base, aucun ID auto-généré retourné.");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(valeursAutoGenerees, preparedStatement,
					connexion);
		}

	}

	@Override
	public Cour trouver(long id, EnseignantDao enseignantDao,
			MatiereDao matiereDao, SalleDao salleDao, ModuleDao moduleDao,
			SemestreDao semestreDao, ClasseDao classeDao) throws DAOException {
		// TODO Auto-generated method stub

		return trouver(enseignantDao, matiereDao, salleDao, moduleDao,
				semestreDao, classeDao, SQL_SELECT_PAR_ID, id);
	}

	private Cour trouver(EnseignantDao enseignantDao, MatiereDao matiereDao,
			SalleDao salleDao, ModuleDao moduleDao, SemestreDao semestreDao,
			ClasseDao classeDao, String sql, Object... objets)
			throws DAOException {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		Cour cour = new Cour();

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
				cour = map(resultSet, enseignantDao, matiereDao, salleDao,
						moduleDao, semestreDao, classeDao);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connexion);
		}
		return cour;
	}

	@Override
	public List<Cour> lister(EnseignantDao enseignantDao,
			MatiereDao matiereDao, SalleDao salleDao, ModuleDao moduleDao,
			SemestreDao semestreDAo, ClasseDao classeDao) throws DAOException {
		// TODO Auto-generated method stub

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		List<Cour> cours = new ArrayList<Cour>();

		try {

			connection = daoFactory.getConnection();
			preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				cours.add(map(resultSet, enseignantDao, matiereDao, salleDao,
						moduleDao, semestreDAo, classeDao));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connection);
		}
		return cours;
	}

	@Override
	public void supprimer(Cour cour) throws DAOException {
		// TODO Auto-generated method stub

		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_DELETE_PAR_ID, true, cour.getId());

			int statut = preparedStatement.executeUpdate();
			if (statut == 0) {
				throw new DAOException(
						"Échec de la suppression du module, aucune ligne supprimée de la table.");
			} else {
				cour.setId(null);
				// semestre.setId_semestre(null);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(preparedStatement, connexion);
		}

	}

	@Override
	public void update(Cour cour, String nom, int id_enseignant, int id_salle,
			int id_matiere, int id_classe, String date, String duree)
			throws DAOException {
		// TODO Auto-generated method stub

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = initialisationRequetePreparee(connexion,
					SQL_UPDATE, true, nom, id_enseignant, id_salle, id_matiere,
					id_classe, date, duree);

			System.out.println("la requete est : " + preparedStatement);

			int statut = preparedStatement.executeUpdate();
			System.out.println("Update effectué");
			if (statut == 0) {
				throw new DAOException(
						"Échec de la modification du cour, aucune ligne modifiée de la table.");
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
	private static Cour map(ResultSet resultSet, EnseignantDao enseignantDao,
			MatiereDao matiereDao, SalleDao salleDao, ModuleDao moduleDao,
			SemestreDao semestreDao, ClasseDao classeDao) throws SQLException {

		Cour cour = new Cour();

		cour.setNom_cour(resultSet.getString("nom_cour"));

		Enseignant enseignant = enseignantDao.trouver(resultSet
				.getLong("enseignant"));
		cour.setEnseignant(enseignant);

		Salle salle = salleDao.trouver(resultSet.getLong("salle"));
		cour.setSalle(salle);

		Matiere matiere = matiereDao.trouver(resultSet.getLong("matiere"),
				moduleDao, semestreDao, classeDao);
		cour.setMatiere(matiere);

		Classe classe = classeDao.trouver(resultSet.getLong("classe"));
		cour.setClasse(classe);

		cour.setDate_cour(resultSet.getString("date"));

		cour.setDuree_cour(resultSet.getInt("duree"));

		cour.setDetail_cour(resultSet.getString("details_cour"));

		return cour;
	}

	@Override
	public List<Cour> listerCourenseignant(EnseignantDao enseignantDao,
			MatiereDao matiereDao, SalleDao salleDao, ModuleDao moduleDao,
			SemestreDao semestreDAo, ClasseDao classeDao, int id_enseignant)
			throws DAOException {
		// TODO Auto-generated method stub

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		List<Cour> cours = new ArrayList<Cour>();

		try {

			connection = daoFactory.getConnection();

			preparedStatement = initialisationRequetePreparee(connection,
					SQL_SELECT_COUR_ENSEIGNANT, false, id_enseignant);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				cours.add(map(resultSet, enseignantDao, matiereDao, salleDao,
						moduleDao, semestreDAo, classeDao));
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			fermeturesSilencieuses(resultSet, preparedStatement, connection);
		}
		return cours;
	}
}
