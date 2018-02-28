package persistence.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import model.Gioca;
import model.GiocaCalciatore;
import persistence.dao.GiocaDao;

public class GiocaDaoJDBC implements GiocaDao {

	private DataSource dataSource;
	
	public GiocaDaoJDBC(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public void save(Gioca l) {
		String query = "INSERT INTO public.gioca(fk_calciatore, fk_giornata, voto) VALUES (?, ?, ?);";
		Connection connection = this.dataSource.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, l.getFkCalciatore());
			ps.setLong(2, l.getFkGiornata());
			ps.setLong(3, l.getVoto());
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
	public void deleteGioca(Gioca l) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "DELETE FROM public.gioca WHERE fk_calciatore = ? AND fk_giornata = ?;";
			PreparedStatement ps = connection.prepareStatement(delete);
			ps.setLong(1, l.getFkCalciatore());
			ps.setLong(2, l.getFkGiornata());
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
	public List<Gioca> getAllGioca() {
		Connection connection = this.dataSource.getConnection();
		List<Gioca> giocas = new LinkedList<>();
		try {
			Gioca l = null;
			PreparedStatement statement;
			String query = "SELECT fk_calciatore, fk_giornata, voto FROM public.gioca;";
			statement = connection.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				l = new Gioca();
				l.setFkCalciatore(rs.getLong("fk_calciatore"));
				l.setFkGiornata(rs.getLong("fk_giornata"));
				l.setVoto(rs.getLong("voto"));
				giocas.add(l);
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
		return giocas;
	}

	@Override
	public Gioca findByPrimaryKey(Gioca l) {
		Connection connection = this.dataSource.getConnection();
		Gioca invito = null;
		try {
			PreparedStatement ps;
			String query = "SELECT fk_calciatore, fk_giornata, voto "
					+ "FROM public.gioca WHERE fk_calciatore = ? AND fk_giornata = ?;";
			ps = connection.prepareStatement(query);
			ps.setLong(1, l.getFkCalciatore());
			ps.setLong(2, l.getFkGiornata());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				invito = new Gioca();
				invito.setFkCalciatore(rs.getLong("fk_calciatore"));
				invito.setFkGiornata(rs.getLong("fk_giornata"));
				invito.setVoto(rs.getLong("voto"));
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
	public List<GiocaCalciatore> getGiocaPerGiornata(Long idGiornata) {
		Connection connection = this.dataSource.getConnection();
		List<GiocaCalciatore> giocas = new ArrayList<>();
		try {
			GiocaCalciatore c = null;
			PreparedStatement statement;
			String query = "SELECT c.squadra, c.nome, g.voto \n" + 
					"FROM public.gioca g, public.calciatore c\n" + 
					"WHERE g.fk_calciatore = c.codice AND g.fk_giornata = ?;";
			statement = connection.prepareStatement(query);
			statement.setLong(1, idGiornata);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				c = new GiocaCalciatore();
				c.setSquadra(rs.getString("squadra"));
				c.setNome(rs.getString("nome"));
				c.setVoto(rs.getLong("voto"));
				giocas.add(c);
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
		return giocas;
	}

}
