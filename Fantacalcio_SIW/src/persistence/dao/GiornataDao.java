package persistence.dao;

import java.util.Date;
import java.util.List;

import model.Giornata;
import persistence.connect.PersistenceException;

public interface GiornataDao {
	public void save(Giornata pr);
	public void deleteGiornata(Giornata pr);
	public List<Giornata> getAllGiornate();
	public Giornata findByPrimaryKey(Long id) throws PersistenceException;
	public List<Giornata> prossimeGiornate(Date data, int limit);
}
