package persistence.dao;

import org.codehaus.jettison.json.JSONObject;

import model.Utente;

public interface UtenteDao {
	public void save(Utente utente); // Create

	public void updateUtente(final String username, final String password); // Update

	public void deleteUtente(String username); // Delete

	public JSONObject getAllUtenti();

	public Utente getUtente(final String username);

}