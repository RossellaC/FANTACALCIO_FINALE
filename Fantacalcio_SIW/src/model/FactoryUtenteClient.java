package model;

public class FactoryUtenteClient {
	public AbstractUtenteFactory getFactory(Genere tipo) {
		switch (tipo) {
		case UTENTE:
			return new MyUtenteFactory();
		case AMMINISTRATORE:
			return new MyAmministratoreFactory();

		default:
			return null;
		}

	}
}
