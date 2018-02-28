package model;

public class MyAmministratoreFactory extends AbstractUtenteFactory {

	@Override
	public Utente getUtente() {
		return null;
	}

	@Override
	public Amministratore getAmministratore() {
		return new Amministratore();
	}

}
