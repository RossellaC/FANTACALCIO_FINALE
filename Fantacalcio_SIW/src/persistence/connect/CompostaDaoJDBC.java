package persistence.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Composta;
import persistence.dao.CompostaDao;

public class CompostaDaoJDBC implements CompostaDao {

	private DataSource dataSource;
	
	public CompostaDaoJDBC(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public void save(Composta l) {
		String query = "INSERT INTO public.composta (fk_calciatore,fk_lega,fk_utente) VALUES (?, ?, ?);";
		Connection connection = this.dataSource.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, l.getFkCalciatore());
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

	@Override
	public void deleteComposta(Composta l) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM public.composta WHERE fk_calciatore = ? AND fk_lega = ? AND fk_utente = ?;";
			PreparedStatement ps = connection.prepareStatement(delete);
			ps.setLong(1, l.getFkCalciatore());
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

	@Override
	public List<Composta> getAllComposta() {
		Connection connection = this.dataSource.getConnection();
		List<Composta> composte = new LinkedList<>();
		try {
			Composta l = null;
			PreparedStatement statement;
			String query = "SELECT fk_calciatore, fk_lega, fk_utente FROM public.composta;";
			statement = connection.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				l = new Composta();
				l.setFkLega(rs.getLong("fk_lega"));
				l.setFkUtente(rs.getLong("fk_utente"));
				l.setFkCalciatore(rs.getLong("fk_calciatore"));
				composte.add(l);
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
		return composte;
	}

	@Override
	public Composta findByPrimaryKey(Composta l) {
		Connection connection = this.dataSource.getConnection();
		Composta composta = null;
		try {
			PreparedStatement ps;
			String query = "SELECT fk_lega, fk_utente, fk_calciatore FROM public.composta WHERE fk_calciatore = ? AND fk_lega = ? AND fk_utente = ?;";
			ps = connection.prepareStatement(query);
			ps.setLong(1, l.getFkCalciatore());
			ps.setLong(2, l.getFkLega());
			ps.setLong(3, l.getFkUtente());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				composta = new Composta();
				composta.setFkUtente(rs.getLong("fk_utente"));
				composta.setFkLega(rs.getLong("fk_lega"));
				composta.setFkCalciatore(rs.getLong("fk_calciatore"));
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
		return composta;
	}

}
