package persistence.dao;

import java.util.List;

import model.Calciatore;
import model.Squadra;

public interface CalciatoreDao {
	public void save(Calciatore calciatore);  // Create
	public Calciatore findByPrimaryKey(Long codice);     // Retrieve
	public List<Calciatore> findAll();     
	public List<Calciatore> findAllLimit(int limit);     
	public void update(Calciatore calciatore); //Update
	public void delete(Calciatore calciatore); //Delete
	public List<Calciatore> ricerca(String s);
	public List<Calciatore> findBySquadra(Squadra s);
	public List<String> getAllSquadre();
	public List<Calciatore> findBySquadraDiCampionato(String s);
}
