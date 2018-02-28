package persistence.connect;

import persistence.dao.AmministratoreDao;
import persistence.dao.CalciatoreDao;
import persistence.dao.CompostaDao;
import persistence.dao.GiocaDao;
import persistence.dao.InvitoDao;
import persistence.dao.LegaDao;
import persistence.dao.PartitaDao;
import persistence.dao.GiornataDao;
import persistence.dao.SquadraDao;
import persistence.dao.UtenteDao;

class PostgresDAOFactory extends DAOFactory {
	public static DataSource dataSource;

	static {
		try {
			Class.forName("org.postgresql.Driver");

			// dataSource=new
			// DataSource("jdbc:postgresql://52.39.164.176:5432/xx","xx","p@xx");
			dataSource = new DataSource("jdbc:postgresql://localhost:5432/Fantacalcio", "postgres", "postgres");
		} catch (Exception e) {
			System.err.println("PostgresDAOFactory.class: failed to load Postgres JDBC driver\n" + e);
			e.printStackTrace();
		}
	}

	@Override
	public CalciatoreDao getCalciatoreDAO() {
		return new CalciatoreDaoJDBC(dataSource);
	}

	@Override
	public UtilDao getUtilDAO() {
		return new UtilDao(dataSource);
	}

	@Override
	public UtenteDao getUtenteDao() {
		return new UtenteDaoJDBC(dataSource);

	}

	@Override
	public GiornataDao getGiornataDAO() {
		return new GiornataDaoJDBC(dataSource);
	}

	@Override
	public LegaDao getLegaDao() {
		return new LegaDaoJDBC(dataSource);
	}

	@Override
	public InvitoDao getInvitoDao() {
		return new InvitoDaoJDBC(dataSource);
	}

	@Override
	public SquadraDao getSquadraDao() {
		return new SquadraDaoJDBC(dataSource);
	}

	@Override
	public CompostaDao getCompostaDao() {
		return new CompostaDaoJDBC(dataSource);
	}

	@Override
	public PartitaDao getPartitaDao() {
		return new PartitaDaoJDBC(dataSource);
	}

	@Override
	public GiocaDao getGiocaDao() {
		return new GiocaDaoJDBC(dataSource);
	}

	@Override
	public AmministratoreDao getAmministratoreDao() {
		return new AmministratoreDaoJDBC2(dataSource);
	}
	
}
