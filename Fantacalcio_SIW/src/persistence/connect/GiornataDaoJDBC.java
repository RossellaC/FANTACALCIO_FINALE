package persistence.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import model.Giornata;
import persistence.dao.GiornataDao;

public class GiornataDaoJDBC implements GiornataDao {

	private DataSource dataSource;
	
	public GiornataDaoJDBC(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(Giornata pr) {
		Connection connection = this.dataSource.getConnection();
		String insert = "INSERT INTO public.giornata (inizio, fine) VALUES (?, ?);";
		try {
			PreparedStatement ps = connection.prepareStatement(insert);
			long secs = pr.getDataInizio().getTime();
			ps.setDate(1, new java.sql.Date(secs));
			secs = pr.getDataFine().getTime();
			ps.setDate(2, new java.sql.Date(secs));
			ps.executeUpdate();
		} catch(SQLException ex) {
			throw new PersistenceException(ex.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public void deleteGiornata(Giornata pr) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM public.giornata WHERE id = ?;";
			PreparedStatement ps = connection.prepareStatement(delete);
			ps.setLong(3, pr.getId());
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
	public List<Giornata> getAllGiornate() {
		Connection connection = this.dataSource.getConnection();
		List<Giornata> partite = new LinkedList<>();
		try {
			Giornata pr;
			PreparedStatement statement;
			String query = "SELECT id, inizio, fine FROM public.giornata;";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				pr = new Giornata();
				pr.setId(result.getLong("id"));				
				pr.setDataInizio(result.getDate("inizio"));
				pr.setDataFine(result.getDate("fine"));				
				partite.add(pr);
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
	public Giornata findByPrimaryKey(Long id) throws PersistenceException {
		Connection connection = this.dataSource.getConnection();
		Giornata pr = null;
		try {
			PreparedStatement ps;
			String query = "SELECT id, inizio, fine FROM public.giornata WHERE id = ?;";
			ps = connection.prepareStatement(query);
			ps.setLong(1, id);
			ResultSet result = ps.executeQuery();
			if (result.next()) {
				pr = new Giornata();
				pr.setId(result.getLong("id"));				
				pr.setDataInizio(result.getDate("inizio"));
				pr.setDataFine(result.getDate("fine"));				
			}
			if(pr == null) {
				throw new PersistenceException("La giornata con chiave " + id + " non esiste.");
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
	public List<Giornata> prossimeGiornate(Date data, int limit) {
		Connection connection = this.dataSource.getConnection();
		List<Giornata> giornate = new ArrayList<>();
		try {
			Giornata pr;
			PreparedStatement statement;
			String query = "SELECT id, inizio, fine FROM public.giornata WHERE inizio > ? LIMIT ?;";
			statement = connection.prepareStatement(query);
			long secs = data.getTime();
			statement.setDate(1, new java.sql.Date(secs));
			statement.setInt(2, limit);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				pr = new Giornata();
				pr.setId(result.getLong("id"));				
				pr.setDataInizio(result.getDate("inizio"));
				pr.setDataFine(result.getDate("fine"));				
				giornate.add(pr);
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
		return giornate;
	}
}
