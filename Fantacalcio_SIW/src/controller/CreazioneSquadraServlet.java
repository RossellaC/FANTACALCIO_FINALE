package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import forms.CreaSquadra;
import model.Lega;
import model.Messaggio;
import model.Squadra;
import model.Utente;
import persistence.connect.DatabaseManager;
import persistence.dao.LegaDao;
import persistence.dao.SquadraDao;

/**
 * Servlet implementation class CreazioneSquadraServlet
 */
@WebServlet("/creazioneSquadra")
public class CreazioneSquadraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreazioneSquadraServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		System.out.println("Utente loggato: " + utente);
		if (utente != null) {
			String sLega = request.getParameter("lega");
			Long l = Long.parseLong(sLega);
			LegaDao legaDao = DatabaseManager.getInstance().getDaoFactory().getLegaDao();
			Lega ll = new Lega(l, 0.0f, "", "", 0L);
			Lega lega = legaDao.findByPrimaryKey(ll);
			request.setAttribute("lega", lega);
			request.getRequestDispatcher("/WEB-INF/pagine/creaSquadra.jsp").forward(request, response);
		} else {
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
		// TODO Scelta del nome della squadra e dei giocatori da inserire in squadra fra
		// quelli disponibili.
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		if (utente != null) {
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String json = "";
			if (br != null) {
				json = br.readLine();
			}
			br.close();
			ObjectMapper om = new ObjectMapper();
			CreaSquadra cs = om.readValue(json, CreaSquadra.class);
			Squadra squadra = new Squadra(cs.getLega(), utente.getId(), cs.getNomeSquadra());
			SquadraDao squadraDao = DatabaseManager.getInstance().getDaoFactory().getSquadraDao();
			boolean risultato = squadraDao.creaSquadra(squadra, cs.getGiocatori());
			if (risultato) {
				response.getWriter().println("{\"status\":true}");
			} else {
				response.getWriter().println("{\"status\":false}");
			}
			response.getWriter().flush();
		} else {
			Messaggio m = new Messaggio();
			m.setTitolo("Errore");
			m.setTesto("Non hai i permessi necessari per accedere a quest'area.");
			m.setLink(request.getContextPath() + "/home");
			m.setTestoLink("Home");
			request.getRequestDispatcher("/WEB-INF/pagine/paginaErrore.jsp").forward(request, response);
		}
	}

}
