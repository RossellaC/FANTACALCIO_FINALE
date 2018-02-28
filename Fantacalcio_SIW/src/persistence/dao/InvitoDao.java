package persistence.dao;

import java.util.List;

import model.Invito;
import model.InvitoLega;

public interface InvitoDao {
	public void save(Invito l);
	public void deleteInvito(Invito l);
	public boolean accettaInvito(Invito l);
	public List<InvitoLega> invitiSenzaSquadra(Long idUtente);
	public List<Invito> getAllInviti();
	public List<Invito> getInvitiPerLega(Long inviante, Long idLega);
	public List<InvitoLega> getInvitiRicevutiUtente(Long idUtente);
	public Invito findByPrimaryKey(Invito l);
}
