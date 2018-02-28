package persistence;

import java.util.Calendar; 
import java.util.Date;
import java.util.List;

import controller.amministrazione.AmministrazioneIndexServlet;
import model.Amministratore;
import model.Calciatore;
import model.Composta;
import model.Gioca;
import model.Invito;
import model.Lega;
import model.Giornata;
import model.Squadra;
import model.Utente;
import persistence.connect.DAOFactory;
import persistence.connect.UtilDao;
import persistence.dao.AmministratoreDao;
import persistence.dao.CalciatoreDao;
import persistence.dao.CompostaDao;
import persistence.dao.GiocaDao;
import persistence.dao.InvitoDao;
import persistence.dao.LegaDao;
import persistence.dao.GiornataDao;
import persistence.dao.SquadraDao;
import persistence.dao.UtenteDao;

public class MainJDBC {

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		cal.set(1993, Calendar.FEBRUARY, 19); // 19 febbraio 1993
		Date date1 = cal.getTime();

		cal.set(1987, Calendar.DECEMBER, 10); // 10 dicembre 1987
		Date date2 = cal.getTime();

		cal.set(1991, Calendar.JUNE, 4); // 4 giugno 1991
		Date date3 = cal.getTime();

		cal.set(1989, Calendar.FEBRUARY, 2); // 2 febbraio 1989
		Date date4 = cal.getTime();

		cal.set(1983, Calendar.JANUARY, 31); // 31 gennaio 1983
		Date date5 = cal.getTime();

		cal.set(1987, Calendar.FEBRUARY, 11); // 11 febbraio 1987
		Date date6 = cal.getTime();

		cal.set(1988, Calendar.MAY, 4); // 4 maggio 1988
		Date date7 = cal.getTime();

		cal.set(1988, Calendar.FEBRUARY, 15); // 15 fenbbraio 1988
		Date date8 = cal.getTime();

		cal.set(1987, Calendar.JULY, 27); // 27 luglio 1987
		Date date9 = cal.getTime();

		cal.set(1988, Calendar.JANUARY, 5); // 5 gennaio 1988
		Date date10 = cal.getTime();

		cal.set(1992, Calendar.JULY, 12); // 12 luglio 1992
		Date date11 = cal.getTime();

		cal.set(1983, Calendar.APRIL, 24); // 24 aprile 1983
		Date date12 = cal.getTime();

		cal.set(1990, Calendar.JANUARY, 4); // 4 gennaio 1990
		Date date13 = cal.getTime();

		cal.set(1984, Calendar.JULY, 14); // 14 luglio 1984
		Date date14 = cal.getTime();

		cal.set(1992, Calendar.OCTOBER, 2); // 2 ottobre 1992
		Date date15 = cal.getTime();

		cal.set(1985, Calendar.NOVEMBER, 10); // 10 novembre 1985
		Date date16 = cal.getTime();

		cal.set(1992, Calendar.FEBRUARY, 5); // 5 febbraio 1992
		Date date17 = cal.getTime();

		cal.set(1995, Calendar.FEBRUARY, 11); // 11 febbraio 1995
		Date date18 = cal.getTime();

		cal.set(1995, Calendar.FEBRUARY, 11); // 11 febbraio 1995
		Date date19 = cal.getTime();

		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		UtilDao util = factory.getUtilDAO();
		util.dropDatabase();

		util.createDatabase();

		// FactoryUtenteClient client= new FactoryUtenteClient();
		// AbstractUtenteFactory utenteFactory=
		// client.getFactory(Genere.UTENTE);
		// Utente utente= utenteFactory.getUtente();
		// System.out.println("creato " + utente.getClass());

		CalciatoreDao calciatoreDao = factory.getCalciatoreDAO();

		Calciatore[] calciatori = { new Calciatore(1L, "Icardi", "Inter", date1, "Attaccante", 35),
				new Calciatore(2L, "HIGUAIN", "Inter", date2, "Attaccante", 36),
				new Calciatore(3L, "INSIGNE", "Inter", date3, "Attaccante", 28),
				new Calciatore(4L, "QUAGLIARELLA", "Inter", date5, "Attaccante", 25),
				new Calciatore(5L, "CALLEJON", "Inter", date6, "Attaccante", 25),
				new Calciatore(6L, "GOMEZ", "Inter", date8, "Attaccante", 24),
				new Calciatore(7L, "KALINIC", "Inter", date10, "Attaccante", 23),
				new Calciatore(8L, "THEREAU", "Napoli", date12, "Attaccante", 20),
				new Calciatore(9L, "Belotti", "Napoli", date13, "Attaccante", 20),
				new Calciatore(10L, "PALACIO", "Napoli", date13, "Attaccante", 20),
				new Calciatore(11L, "DYBALA", "Napoli", date13, "Attaccante", 20),
				new Calciatore(12L, "MANDZUKIC", "Napoli", date13, "Attaccante", 20),
				new Calciatore(13L, "DESTRO", "Napoli", date13, "Attaccante", 20),
				new Calciatore(14L, "IMMOBILE", "Napoli", date13, "Attaccante", 20),
				new Calciatore(15L, "PERICA", "Bologna", date13, "Attaccante", 20),
				new Calciatore(16L, "DEFREL", "Bologna", date13, "Attaccante", 20),
				new Calciatore(17L, "TROTTA", "Bologna", date13, "Attaccante", 20),
				new Calciatore(18L, "MILIK", "Bologna", date13, "Attaccante", 20),
				new Calciatore(19L, "LO FASO", "Bologna", date13, "Attaccante", 20),
				new Calciatore(20L, "SIMEONE", "Bologna", date13, "Attaccante", 20),
				new Calciatore(21L, "ORSOLINI", "Bologna", date13, "Attaccante", 20),

				new Calciatore(22L, "PERISIC", "Lazio", date4, "Centrocampista", 27),
				new Calciatore(23L, "NAINGGOLAN", "Lazio", date7, "Centrocampista", 24),
				new Calciatore(24L, "HAMSIK", "Lazio", date9, "Centrocampista", 23),
				new Calciatore(25L, " VERDI", "Lazio", date11, "Centrocampista", 22),
				new Calciatore(26L, "SAPONARA", "Lazio", date4, "Centrocampista", 27),
				new Calciatore(27L, "PEROTTI", "Lazio", date7, "Centrocampista", 24),
				new Calciatore(28L, "RINCON", "Lazio", date9, "Centrocampista", 23),
				new Calciatore(29L, "BESSA", "Roma", date11, "Centrocampista", 22),
				new Calciatore(30L, "BROZOVIC", "Roma", date4, "Centrocampista", 27),
				new Calciatore(31L, "KHEDIRA", "Roma", date7, "Centrocampista", 24),
				new Calciatore(32L, "STURARO", "Roma", date9, "Centrocampista", 23),
				new Calciatore(33L, "BIGLIA", "Roma", date11, "Centrocampista", 22),
				new Calciatore(34L, "Crecco", "Roma", date4, "Centrocampista", 27),
				new Calciatore(35L, "FELIPE", "Roma", date7, "Centrocampista", 24),
				new Calciatore(36L, "BONAVENTURA", "Crotone", date9, "Centrocampista", 23),
				new Calciatore(37L, "MAURI", "Crotone", date11, "Centrocampista", 22),
				new Calciatore(38L, "ALLAN", "Crotone", date4, "Centrocampista", 27),
				new Calciatore(39L, "DE ROSSI", "Crotone", date7, "Centrocampista", 24),
				new Calciatore(40L, "PJANIC", "Crotone", date9, "Centrocampista", 23),
				new Calciatore(41L, "BASELLI", "Crotone", date11, "Centrocampista", 22),
				new Calciatore(42L, "PELLEGRINI", "Crotone", date4, "Centrocampista", 27),
				new Calciatore(43L, "STROOTMAN", "Udinese", date7, "Centrocampista", 24),
				new Calciatore(44L, "FLORENZI", "Udinese", date9, "Centrocampista", 23),
				new Calciatore(45L, "BIONDINI", "Udinese", date11, "Centrocampista", 22),
				new Calciatore(46L, "GAGLIARDINI", "Udinese", date11, "Centrocampista", 22),

				new Calciatore(47L, "HANDANOVIC", "Udinese", date14, "Portiere", 16),
				new Calciatore(48L, "ALISSON", "Udinese", date15, "Portiere", 16),
				new Calciatore(49L, "Donnarumma", "Udinese", date19, "Portiere", 18),
				new Calciatore(50L, "Buffon", "Fiorentina", date19, "Portiere", 19),
				new Calciatore(51L, "Sportiello", "Fiorentina", date19, "Portiere", 18),
				new Calciatore(52L, "Da Costa", "Fiorentina", date19, "Portiere", 16),
				new Calciatore(53L, "Bizzarri", "Fiorentina", date19, "Portiere", 17),
				new Calciatore(54L, "Skorupski", "Fiorentina", date19, "Portiere", 18),
				new Calciatore(55L, "Sepe", "Fiorentina", date19, "Portiere", 20),
				new Calciatore(56L, "Guerrieri", "Fiorentina", date19, "Portiere", 22),
				new Calciatore(57L, "Berisha", "Milan", date19, "Portiere", 16),
				new Calciatore(58L, "Marchetti", "Milan", date19, "Portiere", 20),
				new Calciatore(59L, "Scuffet", "Milan", date19, "Portiere", 18),
				new Calciatore(60L, "Coppola", "Milan", date19, "Portiere", 19),
				new Calciatore(61L, "Storari", "Milan", date19, "Portiere", 23),
				new Calciatore(62L, "Coraz", "Milan", date19, "Portiere", 18),
				new Calciatore(63L, "Festa", "Milan", date19, "Portiere", 19),

				new Calciatore(64L, "KOLAROV", "Atalanta", date16, "Difensore", 16),
				new Calciatore(65L, "DE VRIJ", "Atalanta", date17, "Difensore", 15),
				new Calciatore(66L, "SKRINIAR", "Atalanta", date18, "Difensore", 15),
				new Calciatore(67L, "Conti", "Atalanta", date16, "Difensore", 16),
				new Calciatore(68L, "Masiello", "Atalanta", date17, "Difensore", 15),
				new Calciatore(69L, "FERRARI", "Atalanta", date18, "Difensore", 15),
				new Calciatore(70L, "Masiello", "Atalanta", date17, "Difensore", 15),
				new Calciatore(71L, "MAIETTA", "Torino", date17, "Difensore", 15),
				new Calciatore(72L, "MASINA", "Torino", date17, "Difensore", 15),
				new Calciatore(73L, "LAURINI", "Torino", date17, "Difensore", 15),
				new Calciatore(74L, "TONELLI", "Torino", date17, "Difensore", 15),
				new Calciatore(75L, "PAVLOVIC", "Torino", date17, "Difensore", 15),
				new Calciatore(76L, "BONUCCI", "Torino", date17, "Difensore", 15),
				new Calciatore(77L, "MIRANDA", "Torino", date17, "Difensore", 15),
				new Calciatore(78L, "BARZAGLI", "Juventus", date17, "Difensore", 15),
				new Calciatore(79L, "RANOCCHIA", "Juventus", date17, "Difensore", 15),
				new Calciatore(80L, "NAGATOMO", "Juventus", date17, "Difensore", 15),
				new Calciatore(81L, "CHIELLINI", "Juventus", date17, "Difensore", 15),
				new Calciatore(82L, "CALABRIA", "Juventus", date17, "Difensore", 15),
				new Calciatore(83L, "DE SCIGLIO", "Juventus", date17, "Difensore", 15),
				new Calciatore(84L, "PALETTA", "Juventus", date17, "Difensore", 15),
				new Calciatore(85L, "ZAPATA", "Milan", date17, "Difensore", 15),
				new Calciatore(86L, "MAGGIO", "Juventus", date17, "Difensore", 15),
				new Calciatore(87L, "ROMAGNOLI", "Torino", date17, "Difensore", 15),
				new Calciatore(88L, "TOROSIDIS", "Fiorentina", date17, "Difensore", 15) };

		for (int i = 0; i < calciatori.length; i++) {
			calciatoreDao.save(calciatori[i]);
		}

		UtenteDao utenteDao = factory.getUtenteDao();

		Utente utente1 = new Utente(0L, "giu91", "rossi", "Giuseppe", "Rossi", "rossi@gmail.com");
		Utente utente2 = new Utente(0L, "gio87", "bianco", "Giovanni", "Bianco", "g@gmail.com");
		Utente utente3 = new Utente(0L, "ciao34", "francy", "Francesco", "Ciao", "ciao@gmail.com");

		utenteDao.save(utente1);
		utenteDao.save(utente2);
		utenteDao.save(utente3);
		
		Utente uuu = utenteDao.getUtente("giu91");
		Utente uuu2 = utenteDao.getUtente("gio87");
		Utente uuu3 = utenteDao.getUtente("ciao34");
		
		Amministratore amministratore = new Amministratore(0L, "admin", "admin", "Rossella","Calabretta", "ross@gmail.com");
		
		AmministratoreDao amministratoreDao = factory.getAmministratoreDao();
		
		amministratoreDao.save(amministratore);

		LegaDao legaDao = factory.getLegaDao();
		Lega[] leghe = { new Lega(0L, 200.0f, "lega1", "descrizione lega1", uuu.getId()) };

		for (Lega l : leghe) {
			legaDao.save(l);
		}

		List<Lega> tutteLeghe = legaDao.getAllLega();

		Lega legaPresente = tutteLeghe.get(0);

		Invito[] inviti = { 
			new Invito(uuu.getId(), uuu2.getId(), legaPresente.getId(), true),
			new Invito(uuu.getId(), uuu3.getId(), legaPresente.getId(), true) 
		};

		InvitoDao invitoDao = factory.getInvitoDao();

		for (Invito i : inviti) {
			invitoDao.save(i);
		}

		SquadraDao squadraDao = factory.getSquadraDao();
		Squadra[] squadre = { 
			new Squadra(legaPresente.getId(), uuu2.getId(), "Inter",130),
			new Squadra(legaPresente.getId(), uuu.getId(), "Milan",175),
			new Squadra(legaPresente.getId(), uuu3.getId(), "Juventus",204) 
		};

		for (Squadra s : squadre) {
			squadraDao.save(s);
		}

		Composta[] composte = { 
				new Composta(1L, legaPresente.getId(), uuu2.getId()),
				new Composta(2L, legaPresente.getId(), uuu.getId()),
				new Composta(3L, legaPresente.getId(), uuu3.getId()),
				new Composta(4L, legaPresente.getId(), uuu2.getId()),
				new Composta(5L, legaPresente.getId(), uuu.getId()),
				new Composta(6L, legaPresente.getId(), uuu3.getId()),
				new Composta(7L, legaPresente.getId(), uuu2.getId()),
				new Composta(8L, legaPresente.getId(), uuu.getId()),
				new Composta(9L, legaPresente.getId(), uuu3.getId()),
				new Composta(10L, legaPresente.getId(), uuu2.getId()),
				new Composta(11L, legaPresente.getId(), uuu.getId()),
				new Composta(12L, legaPresente.getId(), uuu3.getId()),
				new Composta(13L, legaPresente.getId(), uuu2.getId()),
				new Composta(14L, legaPresente.getId(), uuu.getId()),
				new Composta(15L, legaPresente.getId(), uuu3.getId()),
				new Composta(16L, legaPresente.getId(), uuu2.getId()),
				new Composta(17L, legaPresente.getId(), uuu.getId()),
				new Composta(18L, legaPresente.getId(), uuu3.getId()),
				new Composta(19L, legaPresente.getId(), uuu2.getId()),
				new Composta(20L, legaPresente.getId(), uuu.getId()),
				new Composta(21L, legaPresente.getId(), uuu3.getId()),
				new Composta(22L, legaPresente.getId(), uuu2.getId()),
				new Composta(23L, legaPresente.getId(), uuu.getId()),
				new Composta(24L, legaPresente.getId(), uuu3.getId()),
				new Composta(25L, legaPresente.getId(), uuu2.getId()),
				new Composta(26L, legaPresente.getId(), uuu.getId()),
				new Composta(27L, legaPresente.getId(), uuu3.getId()),
				new Composta(28L, legaPresente.getId(), uuu2.getId()),
				new Composta(29L, legaPresente.getId(), uuu.getId()),
				new Composta(30L, legaPresente.getId(), uuu3.getId()),
				new Composta(31L, legaPresente.getId(), uuu2.getId()),
				new Composta(32L, legaPresente.getId(), uuu.getId()),
				new Composta(33L, legaPresente.getId(), uuu3.getId()),
				new Composta(34L, legaPresente.getId(), uuu2.getId()),
				new Composta(35L, legaPresente.getId(), uuu.getId()),
				new Composta(36L, legaPresente.getId(), uuu3.getId()),
				new Composta(37L, legaPresente.getId(), uuu2.getId()),
				new Composta(38L, legaPresente.getId(), uuu.getId()),
				new Composta(39L, legaPresente.getId(), uuu3.getId()),
				new Composta(40L, legaPresente.getId(), uuu2.getId()),
				new Composta(41L, legaPresente.getId(), uuu.getId()),
				new Composta(42L, legaPresente.getId(), uuu3.getId()),
				new Composta(43L, legaPresente.getId(), uuu2.getId()),
				new Composta(44L, legaPresente.getId(), uuu.getId()),
				new Composta(45L, legaPresente.getId(), uuu3.getId()),
				new Composta(46L, legaPresente.getId(), uuu2.getId()),
				new Composta(47L, legaPresente.getId(), uuu.getId()),
				new Composta(48L, legaPresente.getId(), uuu3.getId()),
				new Composta(49L, legaPresente.getId(), uuu2.getId()),
				new Composta(50L, legaPresente.getId(), uuu.getId()),
				new Composta(51L, legaPresente.getId(), uuu3.getId()),
				new Composta(52L, legaPresente.getId(), uuu2.getId()),
				new Composta(53L, legaPresente.getId(), uuu.getId()),
				new Composta(54L, legaPresente.getId(), uuu3.getId()),
				new Composta(55L, legaPresente.getId(), uuu2.getId()),
				new Composta(56L, legaPresente.getId(), uuu.getId()),
				new Composta(57L, legaPresente.getId(), uuu3.getId()),
				new Composta(58L, legaPresente.getId(), uuu2.getId()),
				new Composta(59L, legaPresente.getId(), uuu.getId()),
				new Composta(60L, legaPresente.getId(), uuu3.getId()) };

		CompostaDao compostaDao = factory.getCompostaDao();
		for (Composta c : composte) {
			compostaDao.save(c);
		}

		cal.set(2018, Calendar.FEBRUARY, 20);
		Date ddd17 = cal.getTime();
		cal.set(2018, Calendar.FEBRUARY, 23);
		Date ddd18 = cal.getTime();

		cal.set(2018, Calendar.FEBRUARY, 28);
		Date ddd19 = cal.getTime();
		cal.set(2018, Calendar.MARCH, 2);
		Date ddd20 = cal.getTime();
		
		cal.set(2018, Calendar.MARCH, 6);
		Date ddd21 = cal.getTime();
		cal.set(2018, Calendar.MARCH, 9);
		Date ddd22 = cal.getTime();
		
		cal.set(2018, Calendar.MARCH, 14);
		Date ddd23 = cal.getTime();
		cal.set(2018, Calendar.MARCH, 17);
		Date ddd24 = cal.getTime();
		
		cal.set(2018, Calendar.MARCH, 22);
		Date ddd25 = cal.getTime();
		cal.set(2018, Calendar.MARCH, 25);
		Date ddd26 = cal.getTime();
		
		cal.set(2018, Calendar.MARCH, 29);
		Date ddd27 = cal.getTime();
		cal.set(2018, Calendar.MARCH, 31);
		Date ddd28 = cal.getTime();

		cal.set(2018, Calendar.APRIL, 5);
		Date ddd29 = cal.getTime();
		cal.set(2018, Calendar.APRIL, 8);
		Date ddd30 = cal.getTime();
		

		Giornata[] partiteReali = { 
			new Giornata(0L, ddd17, ddd18),
			new Giornata(0L, ddd19, ddd20), 
			new Giornata(0L, ddd21, ddd22),
			new Giornata(0L, ddd23, ddd24), 
			new Giornata(0L, ddd25, ddd26),
			new Giornata(0L,ddd27, ddd28),
			new Giornata(0L, ddd29, ddd30)
		};

		GiornataDao partitaRealeDao = factory.getGiornataDAO();

		for (Giornata pr : partiteReali) {
			partitaRealeDao.save(pr);
			System.out.println(pr);
		}

		GiocaDao giocaDao = factory.getGiocaDao();

		Gioca[] gioc = { 
				new Gioca(1L, 1L,9l),
				new Gioca(2L, 1L,2l), 
				new Gioca(3L, 1L, 4l),
				new Gioca(4L, 1L,6l),
				new Gioca(5L, 1L,7l), 
				new Gioca(6L, 1L, 6l),
				new Gioca(7L, 1L,9l), 
				new Gioca(8L, 1L,8l),
				new Gioca(9L, 1L, 10l),
				new Gioca(10L, 1L,10l),
				new Gioca(11L, 1L,10l), 
				new Gioca(12L, 1L, 10l),
				new Gioca(13L, 1L,10l), 
				new Gioca(14L, 1L,9l),
				new Gioca(15L, 1L, 9l), 
				new Gioca(16L, 1L,9l),
				new Gioca(17L, 1L,9l), 
				new Gioca(18L, 2L, 8l),
				new Gioca(19L, 2L,7l), 
				new Gioca(20L, 2L,9l),
				new Gioca(21L, 2L,9l), 
				new Gioca(22L, 2L, 8l),
				new Gioca(23L, 2L,9l), 
				new Gioca(24L, 2L, 8l),
				new Gioca(25L, 2L,9l), 
				new Gioca(26L, 2L,6l),
				new Gioca(27L, 2L, 7l), 
				new Gioca(28L, 2L,9l),
				new Gioca(29L, 2L,8l), 
				new Gioca(30L, 2L, 8l),
				new Gioca(31L, 2L,10l), 
				new Gioca(32L, 2L,9l),
				new Gioca(33L, 2L,9l), 
				new Gioca(34L, 2L, 9l),
				new Gioca(35L, 2L, 9l), 
				new Gioca(36L, 2L, 8l),
				new Gioca(37L, 2L,8l), 
				new Gioca(38L, 3L,8l),
				new Gioca(39L, 3L, 8l), 
				new Gioca(40L, 3L,8l),
				new Gioca(41L, 3L,7l), 
				new Gioca(42L, 3L, 7l),
				new Gioca(43L, 3L,7l), 
				new Gioca(44L, 3L,7l),
				new Gioca(45L, 3L, 7l), 
				new Gioca(46L, 3L, 7l),
				new Gioca(47L, 3L,6l), 
				new Gioca(48L, 3L, 6l),
				new Gioca(49L, 3L,6l), 
				new Gioca(50L, 3L,6l),
				new Gioca(51L, 3L, 6l), 
				new Gioca(52L, 3L, 8l),
				new Gioca(53L, 3L,8l), 
				new Gioca(54L, 3L, 8l),
				new Gioca(55L, 3L,8l), 
				new Gioca(56L, 3L,8l),
				new Gioca(57L, 4L, 8l), 
				new Gioca(58L, 4L, 9l),
				new Gioca(59L, 4L,6l), 
				new Gioca(60L, 4L, 5l),
				new Gioca(61L, 4L,4l), 
				new Gioca(62L, 4L,5l),
				new Gioca(63L, 4L, 8l), 
				new Gioca(64L, 4L, 8l),
				new Gioca(65L, 4L,7l), 
				new Gioca(66L, 4L, 5l),
				new Gioca(67L, 4L,9l), 
				new Gioca(68L, 4L,5l),
				new Gioca(69L, 4L, 5l), 
				new Gioca(70L, 4L, 8l),
				new Gioca(71L, 4L, 7l), 
				new Gioca(72L, 4L, 7l),
				new Gioca(73L, 5L,8l), 
				new Gioca(74L, 5L,7l),
				new Gioca(75L, 5L, 7l), 
				new Gioca(76L, 5L, 7l),
				new Gioca(77L, 5L,7l), 
				new Gioca(78L, 5L, 7l),
				new Gioca(79L, 5L, 6l), 
				new Gioca(80L, 5L, 6l),
				new Gioca(81L, 5L,  6l), 
				new Gioca(82L, 5L, 6l),
				new Gioca(83L, 5L,6l), 
				new Gioca(84L, 5L, 6l),
				new Gioca(85L, 5L, 6l), 
				new Gioca(86L, 5L,7l),
				new Gioca(87L, 5L, 8l),
				new Gioca(88L, 5L, 10l) };

		for (Gioca g : gioc) {
			giocaDao.save(g);
			System.out.println(g);
		}
		System.out.println("Elenco calciatori");
		for (Calciatore c : calciatoreDao.findAll()) {
			System.out.println(c);
		}

	}
}
