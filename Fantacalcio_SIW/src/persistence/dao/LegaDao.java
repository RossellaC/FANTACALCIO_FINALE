package persistence.dao;

import java.util.List;

import model.Lega;

public interface LegaDao {
	public void save(Lega l);
	public void update(Lega l);
	public void deleteLega(Lega l);
	public List<Lega> getAllLega();
	public Lega findByPrimaryKey(Lega l);
	public List<Lega> legheUtente(Long idUtente);
	public List<Lega> legheUtentePartecipa(Long idUtente);
}
