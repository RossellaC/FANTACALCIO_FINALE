package persistence.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Lega;
import persistence.dao.LegaDao;

public class LegaDaoJDBC implements LegaDao {
	
	private DataSource dataSource;

	public LegaDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Lega l) {
		String query = "INSERT INTO public.lega (budget_iniziale,nome,descrizione, fk_utente) VALUES (?, ?, ?, ?);";
		Connection connection = this.dataSource.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setFloat(1, l.getBudgetIniziale());
			ps.setString(2, l.getNome());
			ps.setString(3, l.getDescrizione());
			ps.setLong(4, l.getFkUtente());
			ps.executeUpdate();
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
	public void update(Lega l) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update public.lega SET nome=?, descrizione=? WHERE id=?;";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, l.getNome());
			statement.setString(2, l.getDescrizione());
			statement.setLong(3, l.getId());
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
	public void deleteLega(Lega l) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM public.lega WHERE id = ?;";
			PreparedStatement ps = connection.prepareStatement(delete);
			ps.setLong(1, l.getId());
			ps.executeUpdate();
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
	public List<Lega> getAllLega() {
		Connection connection = this.dataSource.getConnection();
		List<Lega> leghe = new LinkedList<>();
		try {
			Lega l;
			PreparedStatement statement;
			String query = "SELECT id, budget_iniziale, nome, descrizione, fk_utente FROM public.lega;";
			statement = connection.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				l = new Lega();
				l.setId(rs.getLong("id"));
				l.setBudgetIniziale(rs.getFloat("budget_iniziale"));
				l.setNome(rs.getString("nome"));
				l.setDescrizione(rs.getString("descrizione"));
				l.setFkUtente(rs.getLong("fk_utente"));
				leghe.add(l);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}	 finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return leghe;
	}

	@Override
	public Lega findByPrimaryKey(Lega l) {
		Connection connection = this.dataSource.getConnection();
		Lega pr = null;
		try {
			PreparedStatement ps;
			String query = "SELECT id, budget_iniziale, nome, descrizione, fk_utente FROM public.lega WHERE id = ?;";
			ps = connection.prepareStatement(query);
			ps.setLong(1, l.getId());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				pr = new Lega();
				pr.setId(rs.getLong("id"));				
				pr.setBudgetIniziale(rs.getFloat("budget_iniziale"));
				pr.setNome(rs.getString("nome"));				
				pr.setDescrizione(rs.getString("descrizione"));
				pr.setFkUtente(rs.getLong("fk_utente"));
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}	 finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return pr;
	}

	@Override
	public List<Lega> legheUtente(Long idUtente) {
		Connection connection = this.dataSource.getConnection();
		List<Lega> leghe = new LinkedList<>();
		try {
			Lega l;
			PreparedStatement statement;
			String query = "SELECT id, budget_iniziale, nome, descrizione, fk_utente FROM public.lega WHERE fk_utente = ?;";
			statement = connection.prepareStatement(query);
			statement.setLong(1, idUtente);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				l = new Lega();
				l.setId(rs.getLong("id"));
				l.setBudgetIniziale(rs.getFloat("budget_iniziale"));
				l.setNome(rs.getString("nome"));
				l.setDescrizione(rs.getString("descrizione"));
				l.setFkUtente(rs.getLong("fk_utente"));
				leghe.add(l);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}	 finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return leghe;
	}

	@Override
	public List<Lega> legheUtentePartecipa(Long idUtente) {
		Connection connection = this.dataSource.getConnection();
		List<Lega> leghe = new LinkedList<>();
		try {
			Lega l;
			PreparedStatement statement;
			String query = "SELECT l.id, l.budget_iniziale, l.nome, l.descrizione, l.fk_utente \n" + 
					"FROM public.lega l, public.squadra s\n" + 
					"WHERE l.id = s.fk_lega AND l.fk_utente <> ? AND s.fk_utente = ?;";
			statement = connection.prepareStatement(query);
			statement.setLong(1, idUtente);
			statement.setLong(2, idUtente);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				l = new Lega();
				l.setId(rs.getLong("id"));
				l.setBudgetIniziale(rs.getFloat("budget_iniziale"));
				l.setNome(rs.getString("nome"));
				l.setDescrizione(rs.getString("descrizione"));
				l.setFkUtente(rs.getLong("fk_utente"));
				leghe.add(l);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}	 finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return leghe;
	}
}
