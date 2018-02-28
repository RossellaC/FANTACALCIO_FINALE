package persistence.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Lega;
import model.Partita;
import persistence.dao.PartitaDao;

public class PartitaDaoJDBC implements PartitaDao {

	private DataSource dataSource;
	
	public PartitaDaoJDBC(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public void save(Partita l) {
		String query = "INSERT INTO public.partita (fk_lega1, fk_utente1, fk_lega2, fk_utente2, fk_giornata, punteggio1, punteggio2, avvenuta) VALUES (?,?,?,?,?,?,?,?);";
		Connection connection = this.dataSource.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, l.getFkLega1());
			ps.setLong(2, l.getFkUtente1());
			ps.setLong(3, l.getFkLega2());
			ps.setLong(4, l.getFkUtente2());
			ps.setLong(5, l.getFkGiornata());
			ps.setLong(6, l.getPunteggio1());
			ps.setLong(7, l.getPunteggio2());
			ps.setBoolean(8, l.isAvvenuta());
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
	public void deletePartita(Partita l) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM public.partita WHERE id = ?;";
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
	public List<Partita> getAllPartita() {
		Connection connection = this.dataSource.getConnection();
		List<Partita> partite = new LinkedList<>();
		try {
			Partita l = null;
			PreparedStatement statement;
			String query = "SELECT id, fk_lega1, fk_utente1, fk_lega2, fk_utente2, fk_giornata, punteggio1, punteggio2, avvenuta FROM public.partita;";
			statement = connection.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				l = new Partita();
				l.setId(rs.getLong("id"));
				l.setFkLega1(rs.getLong("fk_lega1"));
				l.setFkUtente1(rs.getLong("fk_utente1"));
				l.setFkLega2(rs.getLong("fk_lega2"));
				l.setFkUtente2(rs.getLong("fk_utente2"));
				l.setFkGiornata(rs.getLong("fk_giornata"));
				l.setPunteggio1(rs.getInt("punteggio1"));
				l.setPunteggio2(rs.getInt("punteggio2"));
				l.setAvvenuta(rs.getBoolean("avvenuta"));
				partite.add(l);
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
		return partite;
	}

	@Override
	public Partita findByPrimaryKey(Partita l) {
		Connection connection = this.dataSource.getConnection();
		Partita partita = null;
		try {
			PreparedStatement ps;
			String query = "SELECT id, fk_lega1, fk_utente1, fk_lega2, fk_utente2, fk_giornata, punteggio1, punteggio2, avvenuta FROM public.partita "
					+ "WHERE id = ?;";
			ps = connection.prepareStatement(query);
			ps.setLong(1, l.getId());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				partita = new Partita();
				partita.setId(rs.getLong("id"));
				partita.setFkLega1(rs.getLong("fk_lega1"));
				partita.setFkUtente1(rs.getLong("fk_utente1"));
				partita.setFkLega2(rs.getLong("fk_lega2"));
				partita.setFkUtente2(rs.getLong("fk_utente2"));
				partita.setFkGiornata(rs.getLong("fk_giornata"));
				partita.setPunteggio1(rs.getInt("punteggio1"));
				partita.setPunteggio2(rs.getInt("punteggio2"));
				partita.setAvvenuta(rs.getBoolean("avvenuta"));
			}
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
		return partita;
	}

	@Override
	public boolean campionatoIniziato(Lega lega) {
		Connection connection = this.dataSource.getConnection();
		List<Partita> partite = new LinkedList<>();
		try {
			Partita l = null;
			PreparedStatement statement;
			String query = "SELECT id, fk_lega1, fk_utente1, fk_lega2, fk_utente2, fk_giornata, \n" + 
					"  punteggio1, punteggio2, avvenuta\n" + 
					"  FROM public.partita\n" + 
					"  WHERE fk_lega1 = ?;";
			statement = connection.prepareStatement(query);
			statement.setLong(1, lega.getId());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				l = new Partita();
				l.setId(rs.getLong("id"));
				l.setFkLega1(rs.getLong("fk_lega1"));
				l.setFkUtente1(rs.getLong("fk_utente1"));
				l.setFkLega2(rs.getLong("fk_lega2"));
				l.setFkUtente2(rs.getLong("fk_utente2"));
				l.setFkGiornata(rs.getLong("fk_giornata"));
				l.setPunteggio1(rs.getInt("punteggio1"));
				l.setPunteggio2(rs.getInt("punteggio2"));
				l.setAvvenuta(rs.getBoolean("avvenuta"));
				partite.add(l);
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
		return partite.size() > 0;
	}

	@Override
	public void salvaTutte(List<Partita> partite) {
		String query = "INSERT INTO public.partita (fk_lega1, fk_utente1, fk_lega2, fk_utente2, fk_giornata, punteggio1, punteggio2, avvenuta) VALUES (?,?,?,?,?,?,?,?);";
		Connection connection = this.dataSource.getConnection();
		try {
			connection.setAutoCommit(false);
			PreparedStatement ps = connection.prepareStatement(query);
			for (Partita p : partite) {
				ps.setLong(1, p.getFkLega1());
				ps.setLong(2, p.getFkUtente1());
				ps.setLong(3, p.getFkLega2());
				ps.setLong(4, p.getFkUtente2());
				ps.setLong(5, p.getFkGiornata());
				ps.setLong(6, p.getPunteggio1());
				ps.setLong(7, p.getPunteggio2());
				ps.setBoolean(8, p.isAvvenuta());
				ps.executeUpdate();
			}
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

}
