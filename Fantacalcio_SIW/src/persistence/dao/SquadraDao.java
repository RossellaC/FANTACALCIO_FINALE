package persistence.dao;

import java.util.List;

import model.Lega;
import model.Squadra;
import model.Utente;

public interface SquadraDao {
	public void save(Squadra l);
	public void deleteSquadra(Squadra l);
	public void updatePunteggio(Squadra l,int punteggio);
	public boolean utenteHaSquadraPerLega(Utente u, Lega l);
	public List<Squadra> getAllSquadre();
	public List<Squadra> getSquadreLega(Long id);
	public Squadra findByPrimaryKey(Squadra l);
	public boolean creaSquadra(Squadra s, List<Long> idCalciatori);
}
