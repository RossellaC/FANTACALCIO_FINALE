package persistence.dao;

import java.util.List;

import model.Gioca;
import model.GiocaCalciatore;

public interface GiocaDao {
	public void save(Gioca l);
	public void deleteGioca(Gioca l);
	public List<Gioca> getAllGioca();
	public Gioca findByPrimaryKey(Gioca l);
	public List<GiocaCalciatore> getGiocaPerGiornata(Long idGiornata);
}
