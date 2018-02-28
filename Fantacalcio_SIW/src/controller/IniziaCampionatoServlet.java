package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Giornata;
import model.Lega;
import model.Messaggio;
import model.Partita;
import model.Squadra;
import model.Utente;
import persistence.connect.DatabaseManager;
import persistence.dao.GiornataDao;
import persistence.dao.LegaDao;
import persistence.dao.PartitaDao;
import persistence.dao.SquadraDao;

/**
 * Servlet implementation class IniziaCampionatoServlet
 */
@WebServlet("/iniziaCampionato")
public class IniziaCampionatoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IniziaCampionatoServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		Long lega = Long.parseLong(request.getParameter("lega"));
		LegaDao legaDao = DatabaseManager.getInstance().getDaoFactory().getLegaDao();
		Lega daCercare = new Lega(lega, 0f, "", "", 0L);
		Lega trovata = legaDao.findByPrimaryKey(daCercare);
		if (utente != null && trovata != null && trovata.getFkUtente().equals(utente.getId())) {
			PartitaDao partitaDao = DatabaseManager.getInstance().getDaoFactory().getPartitaDao();
			GiornataDao giornataDao = DatabaseManager.getInstance().getDaoFactory().getGiornataDAO();
			SquadraDao squadraDao = DatabaseManager.getInstance().getDaoFactory().getSquadraDao();
			List<Squadra> squadreLega = squadraDao.getSquadreLega(lega);
			System.out.println("Squadre lega: " + squadreLega.size());
			List<Partita> partite = new ArrayList<>();
			for (Squadra s1 : squadreLega) {
				for (Squadra s2 : squadreLega) {
					if (!s1.getFkUtente().equals(s2.getFkUtente())) {
						Partita p = new Partita();
						p.setAvvenuta(false);
						p.setFkLega1(s1.getFkLega());
						p.setFkUtente1(s1.getFkUtente());
						p.setFkLega2(s2.getFkLega());
						p.setFkUtente2(s2.getFkUtente());
						p.setPunteggio1(0);
						p.setPunteggio2(0);
						p.setId(0L);
						p.setFkGiornata(null);
						partite.add(p);
					}
				}
			}
			System.out.println("Partite: " + partite.size());
			List<Giornata> giornate = giornataDao.prossimeGiornate(Calendar.getInstance().getTime(), partite.size());
			if (partite.size() <= giornate.size()) {
				int i = 0;
				for (Partita p : partite) {
					p.setFkGiornata(giornate.get(i).getId());
					i++;
				}
				partitaDao.salvaTutte(partite);
				Messaggio m = new Messaggio();
				m.setTitolo("Ottimo!");
				m.setTesto("Il campionato di questa lega &egrave; iniziato.");
				m.setLink(request.getContextPath() + "/pannelloUtente");
				m.setTestoLink("Profilo");
				request.setAttribute("message", m);
				request.getRequestDispatcher("/WEB-INF/pagine/paginaErrore.jsp").forward(request, response);
			} else {
				Messaggio m = new Messaggio();
				m.setTitolo("Errore");
				m.setTesto("Non ci sono giornate di campionato sufficienti per giocare con queste squadre.");
				m.setLink(request.getContextPath() + "/pannelloUtente");
				m.setTestoLink("Area utente");
				request.getRequestDispatcher("/WEB-INF/pagine/paginaErrore.jsp").forward(request, response);
			}
		} else

		{
			Messaggio m = new Messaggio();
			m.setTitolo("Errore");
			m.setTesto("Non hai i permessi necessari per accedere a quest'area.");
			m.setLink(request.getContextPath() + "/home");
			m.setTestoLink("Home");
			request.getRequestDispatcher("/WEB-INF/pagine/paginaErrore.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
