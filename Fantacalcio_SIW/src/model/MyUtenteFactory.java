package model;

public class MyUtenteFactory extends AbstractUtenteFactory {

	@Override
	public Utente getUtente() {
		return new Utente();
	}

	@Override
	public Amministratore getAmministratore() {
		return null;
	}

}
