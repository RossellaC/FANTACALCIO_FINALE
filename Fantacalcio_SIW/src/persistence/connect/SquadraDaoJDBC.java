package persistence.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.Composta;
import model.Lega;
import model.Squadra;
import model.Utente;
import persistence.dao.SquadraDao;

public class SquadraDaoJDBC implements SquadraDao {

	private DataSource dataSource;
	
	public SquadraDaoJDBC(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public void save(Squadra l) {
		String query = "INSERT INTO public.squadra (fk_lega,fk_utente,nome,punteggio) VALUES (?, ?, ?,?);";
		Connection connection = this.dataSource.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, l.getFkLega());
			ps.setLong(2, l.getFkUtente());
			ps.setString(3, l.getNome());
			ps.setInt(4,l.getPunteggio());
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
	public void deleteSquadra(Squadra l) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM public.squadra WHERE fk_lega = ? AND fk_utente = ?;";
			PreparedStatement ps = connection.prepareStatement(delete);
			ps.setLong(1, l.getFkLega());
			ps.setLong(2, l.getFkUtente());
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
	public List<Squadra> getAllSquadre() {
		Connection connection = this.dataSource.getConnection();
		List<Squadra> squadre = new LinkedList<>();
		try {
			Squadra l = null;
			PreparedStatement statement;
			String query = "SELECT fk_lega, fk_utente, nome, punteggio FROM public.squadra;";
			statement = connection.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				l = new Squadra();
				l.setFkLega(rs.getLong("fk_lega"));
				l.setFkUtente(rs.getLong("fk_utente"));
				l.setNome(rs.getString("nome"));
				l.setPunteggio(rs.getInt("punteggio"));
				squadre.add(l);
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
		return squadre;
	}

	@Override
	public Squadra findByPrimaryKey(Squadra l) {
		Connection connection = this.dataSource.getConnection();
		Squadra invito = null;
		try {
			PreparedStatement ps;
			String query = "SELECT fk_lega, fk_utente, nome, punteggio FROM public.squadra WHERE fk_lega = ? AND fk_utente = ?;";
			ps = connection.prepareStatement(query);
			ps.setLong(1, l.getFkLega());
			ps.setLong(2, l.getFkUtente());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				invito = new Squadra();
				invito.setFkUtente(rs.getLong("fk_utente"));
				invito.setFkLega(rs.getLong("fk_lega"));
				invito.setNome(rs.getString("nome"));
				invito.setPunteggio(rs.getInt("punteggio"));
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
		return invito;
	}

	@Override
	public List<Squadra> getSquadreLega(Long id) {
		Connection connection = this.dataSource.getConnection();
		List<Squadra> squadre = new LinkedList<>();
		try {
			Squadra l = null;
			PreparedStatement statement;
			String query = "SELECT fk_lega, fk_utente, nome, punteggio "
					+ "FROM public.squadra WHERE fk_lega = ? ORDER BY punteggio DESC;";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				l = new Squadra();
				l.setFkLega(rs.getLong("fk_lega"));
				l.setFkUtente(rs.getLong("fk_utente"));
				l.setNome(rs.getString("nome"));
				l.setPunteggio(rs.getInt("punteggio"));
				squadre.add(l);
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
		return squadre;
	}
	
	public boolean creaSquadra(Squadra s, List<Long> idCalciatori) {
		List<Composta> composte = new ArrayList<>();
		for(Long c : idCalciatori) {
			Composta composta = new Composta(c, s.getFkLega(), s.getFkUtente());
			composte.add(composta);
		}
		String query1 = "INSERT INTO public.squadra (fk_lega,fk_utente,nome) VALUES (?, ?, ?);";
		String query2 = "INSERT INTO public.composta (fk_calciatore,fk_lega,fk_utente) VALUES (?, ?, ?);";
		Connection connection = this.dataSource.getConnection();
		try {
			connection.setAutoCommit(false);
			PreparedStatement ps1 = connection.prepareStatement(query1);
			PreparedStatement ps2 = connection.prepareStatement(query2);
			ps1.setLong(1, s.getFkLega());
			ps1.setLong(2, s.getFkUtente());
			ps1.setString(3, s.getNome());
			ps1.executeUpdate();
			for(Composta c : composte) {
				ps2.setLong(1, c.getFkCalciatore());
				ps2.setLong(2, c.getFkLega());
				ps2.setLong(3, c.getFkUtente());
				ps2.executeUpdate();
			}
			connection.commit();
			return true;
		} catch(Exception e) {
			try {
				connection.rollback();
				return false;
			} catch (SQLException e1) {
				throw new PersistenceException(e.getMessage());
			}
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public boolean utenteHaSquadraPerLega(Utente u, Lega lega) {
		Connection connection = this.dataSource.getConnection();
		List<Squadra> squadre = new LinkedList<>();
		try {
			Squadra l = null;
			PreparedStatement statement;
			String query = "SELECT fk_lega, fk_utente, nome, punteggio "
					+ "FROM public.squadra WHERE fk_lega = ? AND fk_utente = ?;";
			statement = connection.prepareStatement(query);
			statement.setLong(1, lega.getId());
			statement.setLong(2, u.getId());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				l = new Squadra();
				l.setFkLega(rs.getLong("fk_lega"));
				l.setFkUtente(rs.getLong("fk_utente"));
				l.setNome(rs.getString("nome"));
				l.setPunteggio(rs.getInt("punteggio"));
				squadre.add(l);
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
		return squadre.size() > 0;
	}

	@Override
	public void updatePunteggio(Squadra l,int punteggio) {
		Connection connection = this.dataSource.getConnection();
		try {
			PreparedStatement ps;
			String query = "UPDATE squadra SET punteggio = ? WHERE fk_lega = ? AND fk_utente = ?;";
			ps = connection.prepareStatement(query);
			ps.setInt(1, punteggio);
			ps.setLong(2, l.getFkLega());
			ps.setLong(3, l.getFkUtente());
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
}
