package persistence.dao;

import org.codehaus.jettison.json.JSONObject;

import model.Amministratore;
import model.Utente;

public interface AmministratoreDao {
	public void save(Amministratore utente); // Create

	public void updateUtente(final String username, final String password); // Update

	public void deleteUtente(String username); // Delete

	public JSONObject getAllUtenti();

	public Amministratore getUtente(final String username);

}