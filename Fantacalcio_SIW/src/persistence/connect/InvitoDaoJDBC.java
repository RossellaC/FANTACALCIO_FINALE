package persistence.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.Invito;
import model.InvitoLega;
import persistence.dao.InvitoDao;

public class InvitoDaoJDBC implements InvitoDao {

	private DataSource dataSource;
	
	public InvitoDaoJDBC(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public void save(Invito l) {
		String query = "INSERT INTO public.invito (fk_utente_invia, fk_utente_riceve, fk_lega, accettazione) VALUES (?, ?, ?, ?);";
		Connection connection = this.dataSource.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1, l.getFkUtenteInvia());
			ps.setLong(2, l.getFkUtenteRiceve());
			ps.setLong(3, l.getFkLega());
			ps.setBoolean(4, l.isAccettazione());
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
	public void deleteInvito(Invito l) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM public.invito WHERE fk_lega = ? AND fk_utente_riceve = ?;";
			PreparedStatement ps = connection.prepareStatement(delete);
			ps.setLong(1, l.getFkLega());
			ps.setLong(2, l.getFkUtenteRiceve());
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
	public boolean accettaInvito(Invito l) {
		Connection connection = this.dataSource.getConnection();
		try {
			String upd = "UPDATE public.invito SET accettazione = TRUE WHERE fk_utente_riceve=? AND fk_lega=?;";
			PreparedStatement ps = connection.prepareStatement(upd);
			ps.setLong(1, l.getFkUtenteRiceve());
			ps.setLong(2, l.getFkLega());
			int n = ps.executeUpdate();
			return n > 0;
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
	public List<InvitoLega> invitiSenzaSquadra(Long idUtente) {
		Connection connection = this.dataSource.getConnection();
		List<InvitoLega> inviti = new LinkedList<>();
		try {
			InvitoLega l = null;
			PreparedStatement statement;
			String query = "SELECT i.fk_utente_invia AS mittente, i.fk_utente_riceve, i.fk_lega, l.nome, i.accettazione\n" + 
					"FROM public.invito i, public.lega l\n" + 
					"WHERE i.fk_lega = l.id AND i.fk_utente_riceve = ? AND i.accettazione = TRUE \n" + 
					"	AND (i.fk_utente_riceve, i.fk_lega) NOT IN (\n" + 
					"		SELECT fk_utente, fk_lega\n" + 
					"		FROM public.squadra s\n" + 
					"		WHERE s.fk_utente = i.fk_utente_riceve\n" + 
					"	);";
			statement = connection.prepareStatement(query);
			statement.setLong(1, idUtente);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				l = new InvitoLega();
				l.setMittente(rs.getString("mittente"));
				l.setFkUtenteRiceve(rs.getLong("fk_utente_riceve"));
				l.setFkLega(rs.getLong("fk_lega"));
				l.setNomeLega(rs.getString("nome"));
				l.setAccettazione(rs.getBoolean("accettazione"));
				inviti.add(l);
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
		return inviti;
	}

	@Override
	public List<Invito> getAllInviti() {
		Connection connection = this.dataSource.getConnection();
		List<Invito> inviti = new LinkedList<>();
		try {
			Invito l = null;
			PreparedStatement statement;
			String query = "SELECT fk_utente_invia, fk_utente_riceve, fk_lega, accettazione FROM public.invito;";
			statement = connection.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				l = new Invito();
				l.setFkUtenteInvia(rs.getLong("fk_utente_invia"));
				l.setFkUtenteRiceve(rs.getLong("fk_utente_riceve"));
				l.setFkLega(rs.getLong("fk_lega"));
				l.setAccettazione(rs.getBoolean("accettazione"));
				inviti.add(l);
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
		return inviti;
	}

	@Override
	public Invito findByPrimaryKey(Invito l) {
		Connection connection = this.dataSource.getConnection();
		Invito invito = null;
		try {
			PreparedStatement ps;
			String query = "SELECT fk_utente_invia, fk_utente_riceve, fk_lega, accettazione FROM public.invito WHERE fk_utente_riceve = ? AND fk_lega = ?;";
			ps = connection.prepareStatement(query);
			ps.setLong(1, l.getFkUtenteRiceve());
			ps.setLong(2, l.getFkLega());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				invito = new Invito();
				invito.setFkUtenteInvia(rs.getLong("fk_utente_invia"));
				invito.setFkUtenteRiceve(rs.getLong("fk_utente_riceve"));
				invito.setFkLega(rs.getLong("fk_lega"));
				invito.setAccettazione(rs.getBoolean("accettazione"));
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
	public List<Invito> getInvitiPerLega(Long inviante, Long idLega) {
		Connection connection = this.dataSource.getConnection();
		List<Invito> inviti = new LinkedList<>();
		try {
			Invito l = null;
			PreparedStatement statement;
			String query = "SELECT fk_utente_invia, fk_utente_riceve, fk_lega, accettazione "
					+ "FROM public.invito "
					+ "WHERE fk_utente_invia = ? AND fk_lega = ?;";
			statement = connection.prepareStatement(query);
			statement.setLong(1, inviante);
			statement.setLong(2, idLega);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				l = new Invito();
				l.setFkUtenteInvia(rs.getLong("fk_utente_invia"));
				l.setFkUtenteRiceve(rs.getLong("fk_utente_riceve"));
				l.setFkLega(rs.getLong("fk_lega"));
				l.setAccettazione(rs.getBoolean("accettazione"));
				inviti.add(l);
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
		return inviti;
	}

	@Override
	public List<InvitoLega> getInvitiRicevutiUtente(Long idUtente) {
		Connection connection = this.dataSource.getConnection();
		List<InvitoLega> inviti = new LinkedList<>();
		try {
			InvitoLega l = null;
			PreparedStatement statement;
			String query = "SELECT u.id, u.username AS mittente, i.fk_utente_riceve, "
					+ "i.fk_lega, l.nome, i.accettazione\n" + 
					"FROM public.invito i, public.lega l, public.utente u\n" + 
					"WHERE i.fk_lega = l.id AND i.accettazione = FALSE "
					+ "AND i.fk_utente_invia = u.id AND i.fk_utente_riceve = ?;";
			statement = connection.prepareStatement(query);
			statement.setLong(1, idUtente);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				l = new InvitoLega();
				l.setMittente(rs.getString("mittente"));
				l.setFkUtenteRiceve(rs.getLong("fk_utente_riceve"));
				l.setFkLega(rs.getLong("fk_lega"));
				l.setNomeLega(rs.getString("nome"));
				l.setAccettazione(rs.getBoolean("accettazione"));
				inviti.add(l);
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
		return inviti;
	}

}
