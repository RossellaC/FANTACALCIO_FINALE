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

public abstract class DAOFactory {

	
	public static final int HSQLDB = 1;
	
	
	public static final int POSTGRESQL = 2;
	
	public static DAOFactory getDAOFactory(int whichFactory) {
		switch ( whichFactory ) {
		
		case HSQLDB:
			return null;//new HsqldbDAOFactory();
		case POSTGRESQL:
			return new PostgresDAOFactory();
		default:
			return null;
		}
	}
	
	
	public abstract CalciatoreDao getCalciatoreDAO();
	public abstract UtenteDao getUtenteDao();
	public abstract AmministratoreDao getAmministratoreDao();
    public abstract persistence.connect.UtilDao getUtilDAO();
    public abstract GiornataDao getGiornataDAO();
    public abstract LegaDao getLegaDao();
    public abstract InvitoDao getInvitoDao();
    public abstract SquadraDao getSquadraDao();
    public abstract CompostaDao getCompostaDao();
    public abstract PartitaDao getPartitaDao();
    public abstract GiocaDao getGiocaDao();
}
