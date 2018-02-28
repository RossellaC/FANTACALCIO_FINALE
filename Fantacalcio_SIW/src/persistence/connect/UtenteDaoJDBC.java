package persistence.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import model.Factories;
import model.Utente;

import persistence.dao.UtenteDao;

public class UtenteDaoJDBC implements UtenteDao {
	private DataSource dataSource;

	public UtenteDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Utente utente) {
		Connection connection = this.dataSource.getConnection();
		DatabaseManager.getInstance().getDaoFactory().getUtenteDao();

		try {
			String insert = "insert into utente(username,password,nome,cognome,email) values(?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, utente.getUsername());
			statement.setString(2, utente.getPassword());
			statement.setString(3, utente.getNome());
			statement.setString(4, utente.getCognome());
			statement.setString(5, utente.getEmail());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}

	}

	@Override
	public void updateUtente(String username, String password) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update utente SET username=?,password=? WHERE username=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, username);
			statement.setString(2, password);
			statement.setString(3, username);

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public void deleteUtente(String email) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM utente WHERE email=?";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, email);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	public JSONObject getAllUtenti() {
		JSONObject json = new JSONObject();
		JSONArray utenti = new JSONArray();
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "select * from utente ;";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet mResultSet = ps.executeQuery();

			if (mResultSet != null) {
				while (mResultSet.next()) {
					JSONObject utente = new JSONObject();
					utente.put("username", mResultSet.getString("username"));
					utente.put("password", mResultSet.getString("password"));
					utente.put("nome", mResultSet.getString("nome"));
					utente.put("cognome", mResultSet.getString("cognome"));
					utente.put("email", mResultSet.getString("email"));
					utenti.put(utente);

				}
			}

			json.put("utenti", utenti);
			connection.close();
		} catch (Exception e) {
		}

		return json;
	}

	@Override
	public Utente getUtente(String username) {
		Utente utente = Factories.getIstance().makeUtente();
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "SELECT id, nome, cognome, username, email, password "
					+ "FROM public.utente WHERE username=?;";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				utente.setId(rs.getLong("id"));
				utente.setNome(rs.getString("nome"));
				utente.setCognome(rs.getString("cognome"));
				utente.setUsername(rs.getString("username"));
				utente.setPassword(rs.getString("password"));
				utente.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			System.out.println("Utente non presente nel DataBase!!!");
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return utente;
	}

	public boolean validate(String username, String password) {
		Utente utente = getUtente(username);

		if (utente.getUsername() != null && utente.getPassword().equals(password))
			return true;
		return false;
	}
}
