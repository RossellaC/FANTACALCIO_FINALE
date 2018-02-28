package persistence.connect;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.Calciatore;
import model.Squadra;
import persistence.dao.CalciatoreDao;

public class CalciatoreDaoJDBC  implements CalciatoreDao {
	private DataSource dataSource;

	public CalciatoreDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void save(Calciatore calciatore) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into calciatore(nome,squadra, dataNascita,ruolo,costo ) values (?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
//			statement.setLong(1, calciatore.getCodice());
			statement.setString(1, calciatore.getNome());
			statement.setString(2, calciatore.getSquadra());
			long secs = calciatore.getDataNascita().getTime();
			statement.setDate(3, new java.sql.Date(secs));
			statement.setString(4, calciatore.getRuolo());
			statement.setDouble(5, calciatore.getCosto());
			
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

	public Calciatore findByPrimaryKey(Long codice) {
		Connection connection = this.dataSource.getConnection();
		Calciatore calciatore = null;
		try {
			PreparedStatement statement;
			String query = "select * from calciatore where codice = ?";
			statement = connection.prepareStatement(query);
			statement.setLong(1,codice);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				calciatore = new Calciatore();
				calciatore.setCodice(result.getLong("codice"));				
				calciatore.setNome(result.getString("nome"));
				calciatore.setSquadra(result.getString("squadra"));
				long secs = result.getDate("dataNascita").getTime();
				calciatore.setDataNascita(new java.util.Date(secs));
				calciatore.setRuolo(result.getString("ruolo"));
				calciatore.setCosto(result.getDouble("costo"));
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
		return calciatore;
	}

	public List<Calciatore> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<Calciatore> calciatori = new LinkedList<>();
		try {
			Calciatore calciatore;
			PreparedStatement statement;
			String query = "select * from calciatore";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				calciatore = new Calciatore();
				calciatore.setCodice(result.getLong("codice"));				
				calciatore.setNome(result.getString("nome"));
				calciatore.setSquadra(result.getString("squadra"));
				long secs = result.getDate("dataNascita").getTime();
				calciatore.setDataNascita(new java.util.Date(secs));
				calciatore.setRuolo(result.getString("ruolo"));
				calciatore.setCosto(result.getDouble("costo"));
				
				calciatori.add(calciatore);
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
		return calciatori;
	}
	
	@Override
	public List<Calciatore> findAllLimit(int limit) {
		Connection connection = this.dataSource.getConnection();
		List<Calciatore> calciatori = new LinkedList<>();
		try {
			Calciatore calciatore;
			PreparedStatement statement;
			String query = "select * from calciatore order by codice limit ?;";
			statement = connection.prepareStatement(query);
			statement.setInt(1, limit);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				calciatore = new Calciatore();
				calciatore.setCodice(result.getLong("codice"));				
				calciatore.setNome(result.getString("nome"));
				calciatore.setSquadra(result.getString("squadra"));
				long secs = result.getDate("dataNascita").getTime();
				calciatore.setDataNascita(new java.util.Date(secs));
				calciatore.setRuolo(result.getString("ruolo"));
				calciatore.setCosto(result.getDouble("costo"));
				
				calciatori.add(calciatore);
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
		return calciatori;
	}

	public void update(Calciatore calciatore) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update calciatore SET nome = ?, squadra = ?, dataNascita = ?, ruolo = ? , costo =?  WHERE codice=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, calciatore.getNome());
			statement.setString(2,  calciatore.getSquadra());
			long secs =  calciatore.getDataNascita().getTime();
			statement.setDate(3, new java.sql.Date(secs));
			statement.setString(4,  calciatore.getRuolo());
			statement.setDouble(5,  calciatore.getCosto());
			statement.setLong(6, calciatore.getCodice());
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

	public void delete(Calciatore calciatore) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM calciatore WHERE codice = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setLong(1, calciatore.getCodice());
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
	public List<Calciatore> ricerca(String s) {
		Connection connection = this.dataSource.getConnection();
		List<Calciatore> calciatori = new LinkedList<>();
		try {
			Calciatore calciatore;
			PreparedStatement statement;
			String query = "SELECT codice, nome, squadra, datanascita, ruolo, costo\n" + 
					"FROM public.calciatore\n" + 
					"WHERE UPPER(nome) LIKE UPPER(CONCAT('%', ?, '%')) \n" + 
					"OR UPPER(ruolo) LIKE UPPER(CONCAT('%', ?, '%')) \n" + 
					"OR UPPER(squadra) LIKE UPPER(CONCAT('%', ?, '%'));";
			statement = connection.prepareStatement(query);
			statement.setString(1, s);
			statement.setString(2, s);
			statement.setString(3, s);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				calciatore = new Calciatore();
				calciatore.setCodice(result.getLong("codice"));				
				calciatore.setNome(result.getString("nome"));
				calciatore.setSquadra(result.getString("squadra"));
				long secs = result.getDate("dataNascita").getTime();
				calciatore.setDataNascita(new java.util.Date(secs));
				calciatore.setRuolo(result.getString("ruolo"));
				calciatore.setCosto(result.getDouble("costo"));
				
				calciatori.add(calciatore);
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
		return calciatori;
	}

	@Override
	public List<Calciatore> findBySquadra(Squadra s) {
		Connection connection = this.dataSource.getConnection();
		List<Calciatore> calciatori = new LinkedList<>();
		try {
			Calciatore calciatore;
			PreparedStatement statement;
			String query = "SELECT c.codice, c.nome, c.squadra, c.datanascita, c.ruolo, c.costo\n" + 
					"FROM public.calciatore c, public.composta co, public.squadra s\n" + 
					"WHERE c.codice = co.fk_calciatore AND co.fk_lega = s.fk_lega AND co.fk_utente = s.fk_utente\n" + 
					"	AND s.fk_lega = ? AND s.fk_utente = ?;";
			statement = connection.prepareStatement(query);
			statement.setLong(1, s.getFkLega());
			statement.setLong(2, s.getFkUtente());
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				calciatore = new Calciatore();
				calciatore.setCodice(result.getLong("codice"));				
				calciatore.setNome(result.getString("nome"));
				calciatore.setSquadra(result.getString("squadra"));
				long secs = result.getDate("dataNascita").getTime();
				calciatore.setDataNascita(new java.util.Date(secs));
				calciatore.setRuolo(result.getString("ruolo"));
				calciatore.setCosto(result.getDouble("costo"));
				calciatori.add(calciatore);
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
		return calciatori;
	}

	@Override
	public List<String> getAllSquadre() {
		Connection connection = this.dataSource.getConnection();
		List<String> squadre = new ArrayList<>();
		try {
			String squadra;
			PreparedStatement statement;
			String query = "SELECT DISTINCT squadra FROM public.calciatore ORDER BY squadra ASC;";
			statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				squadra = result.getString("squadra");
				squadre.add(squadra);
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
	public List<Calciatore> findBySquadraDiCampionato(String s) {
		Connection connection = this.dataSource.getConnection();
		List<Calciatore> calciatori = new LinkedList<>();
		try {
			Calciatore calciatore;
			PreparedStatement statement;
			String query = "SELECT codice,nome,squadra,datanascita,ruolo,costo \n" + 
					"FROM public.calciatore WHERE squadra = ?;";
			statement = connection.prepareStatement(query);
			statement.setString(1, s);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				calciatore = new Calciatore();
				calciatore.setCodice(result.getLong("codice"));				
				calciatore.setNome(result.getString("nome"));
				calciatore.setSquadra(result.getString("squadra"));
				long secs = result.getDate("dataNascita").getTime();
				calciatore.setDataNascita(new java.util.Date(secs));
				calciatore.setRuolo(result.getString("ruolo"));
				calciatore.setCosto(result.getDouble("costo"));
				calciatori.add(calciatore);
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
		return calciatori;
	}
}
