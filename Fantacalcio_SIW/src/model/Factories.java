package model;

public class Factories {

	private static Factories factories = new Factories();
	private FactoryUtenteClient client = new FactoryUtenteClient();
	private AbstractUtenteFactory abstractUtenteFactory;

	private Factories() {
	}

	public static Factories getIstance() {
		return factories;
	}

	public Utente makeUtente() {
		abstractUtenteFactory = client.getFactory(Genere.UTENTE);
		Utente utente = abstractUtenteFactory.getUtente();
		return utente;
	}

	public Amministratore makeAmministratore() {
		abstractUtenteFactory = client.getFactory(Genere.AMMINISTRATORE);
		Amministratore amministratore = abstractUtenteFactory.getAmministratore();
		return amministratore;
	}

}
