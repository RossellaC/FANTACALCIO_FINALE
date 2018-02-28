package persistence;

import persistence.connect.DAOFactory;
import persistence.connect.UtilDao;

public class ProvaJDBC {

	public static void main(String[] args) {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		UtilDao util = factory.getUtilDAO();
		util.dropDatabase();
		util.createDatabase();
	}
	
//	public static void main(String[] args) {
//		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
//		UtenteDao utenteDao = factory.getUtenteDao();
//		LegaDao legaDao = factory.getLegaDao();
//		InvitoDao invitoDao = factory.getInvitoDao();
//		SquadraDao squadraDao = factory.getSquadraDao();
//		CompostaDao compostaDao = factory.getCompostaDao();
//		PartitaDao partitaDao = factory.getPartitaDao(); 
//		
//		Utente u1 = new Utente("ciccio", "ciccio", "Ciccio", "Cicci", "ciccio@cicci.it"); 
//		Utente u2 = new Utente("franco", "franco", "Franco", "Franchi", "franco@franchi.it");
//		Utente u3 = new Utente("gigi", "gigi", "Gigi", "Gigini", "gigi@gigini.it");
//		
//		utenteDao.save(u1);
//		utenteDao.save(u2);
//		utenteDao.save(u3);
//		
//		Lega l1 = new Lega(0L, 250.0f, "Lega 1", "Descrizione della Lega 1", u1.getEmail());
//		
//		legaDao.save(l1);
//		
//		Lega trovata = null;
//		List<Lega> leghe = legaDao.getAllLega();
//		for (Lega ll : leghe) {
//			trovata = ll;
//			break;
//		}
//		
//		Invito i1 = new Invito(u1.getEmail(), u2.getEmail(), trovata.getId(), true);
//		Invito i2 = new Invito(u1.getEmail(), u3.getEmail(), trovata.getId(), true);
//		
//		invitoDao.save(i1);
//		invitoDao.save(i2);
//		
//		List<Invito> inviti = invitoDao.getAllInviti();
//		
//		System.out.println("Trovati tutti");
//		for (Invito i : inviti) {
//			System.out.println(i);
//		}
//		
//		Invito dt = new Invito();
//		dt.setFkLega(trovata.getId());
//		dt.setFkUtenteRiceve(u2.getEmail());
//		Invito ii = invitoDao.findByPrimaryKey(dt);
//		
//		System.out.println("Trovato singolo:");
//		System.out.println(ii);
//		
//		System.out.println("Inserisco squadre");
//		Squadra s1 = new Squadra(trovata.getId(), u1.getEmail(), "Squadra 1");
//		Squadra s2 = new Squadra(trovata.getId(), u2.getEmail(), "Squadra 2");
//		Squadra s3 = new Squadra(trovata.getId(), u3.getEmail(), "Squadra 3");
//		
//		squadraDao.save(s1);
//		squadraDao.save(s2);
//		squadraDao.save(s3);
//		
//		System.out.println("Tutte le squadre");
//		List<Squadra> squadre = squadraDao.getAllSquadre();
//		for (Squadra s : squadre) {
//			System.out.println(s);
//		}
//		
//		System.out.println("Cerco squadra singola");
//		Squadra daTrovare = new Squadra();
//		daTrovare.setFkLega(trovata.getId());
//		daTrovare.setFkUtente(u1.getEmail());
//		Squadra st = squadraDao.findByPrimaryKey(daTrovare);
//		System.out.println(st);
//		
//		System.out.println("Inserisco composte");
//		Composta c1 = new Composta("0011", st.getFkLega(), st.getFkUtente()); 
//		Composta c2 = new Composta("0018", st.getFkLega(), st.getFkUtente()); 
//		Composta c3 = new Composta("0023", st.getFkLega(), st.getFkUtente()); 
//		Composta c4 = new Composta("0034", st.getFkLega(), st.getFkUtente()); 
//
//		compostaDao.save(c1);
//		compostaDao.save(c2);
//		compostaDao.save(c3);
//		compostaDao.save(c4);
//		
//		System.out.println("Tutte le composte");
//		List<Composta> composte = compostaDao.getAllComposta();
//		for (Composta c : composte) {
//			System.out.println(c);
//		}
//		
//		System.out.println("Cerco composta singola");
//		Composta compDaTrov = new Composta("0023", st.getFkLega(), st.getFkUtente());
//		Composta compTrovata = compostaDao.findByPrimaryKey(compDaTrov);
//		System.out.print("Trovata:");
//		System.out.println(compTrovata);		
//		
//		System.out.println("Inserisco partite");
//		Calendar cal = Calendar.getInstance(); 
//		cal.set(2017, Calendar.MARCH, 12);
//		Partita p1= new Partita(s1.getFkLega(), s1.getFkUtente(), s2.getFkLega(), s2.getFkUtente(), cal.getTime() , 2L, 3L);
//		Partita p2= new Partita(s1.getFkLega(), s1.getFkUtente(), s3.getFkLega(), s3.getFkUtente(), cal.getTime() , 1L, 0L);
//
//		partitaDao.save(p1);
//		partitaDao.save(p2);
//		
//		System.out.println("Tutte le partite");
//		List<Partita> partite = partitaDao.getAllPartita();
//		for (Partita p : partite) {
//			System.out.println(p);
//		}
//		
//		System.out.println("Cerco partita singola");
//		Partita parTrovata = partitaDao.findByPrimaryKey(p1);
//		System.out.print("Trovata:");
//		System.out.println(parTrovata);
//		
//		System.out.print("Inizio cancellazione...");
//		System.out.println("Elimino tutte le partite");
//		for (Partita p : partite) {
//			partitaDao.deletePartita(p);
//		}
//		System.out.println("Elimino tutte le composte");
//		for (Composta c : composte) {
//			compostaDao.deleteComposta(c);
//		}
//		
//		squadraDao.deleteSquadra(s1);
//		squadraDao.deleteSquadra(s2);
//		squadraDao.deleteSquadra(s3);
//		invitoDao.deleteInvito(i1);
//		invitoDao.deleteInvito(i2);
//		legaDao.deleteLega(trovata);
//		utenteDao.deleteUtente(u3.getEmail());
//		utenteDao.deleteUtente(u2.getEmail());
//		utenteDao.deleteUtente(u1.getEmail());
//		System.out.println("Fatto");
//	}

}
