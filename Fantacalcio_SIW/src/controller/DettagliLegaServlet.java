package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Lega;
import model.Messaggio;
import model.Squadra;
import model.Utente;
import persistence.connect.DatabaseManager;
import persistence.dao.LegaDao;
import persistence.dao.PartitaDao;
import persistence.dao.SquadraDao;

/**
 * Servlet implementation class DettagliLegaServlet
 */
@WebServlet("/dettagliLega")
public class DettagliLegaServlet extends HttpServlet {

	private static final long serialVersionUID = 8534933708493780744L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public DettagliLegaServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long idLega = Long.parseLong(request.getParameter("id"));
		Utente loggato = (Utente) request.getSession().getAttribute("utente"); 
		LegaDao legaDao = DatabaseManager.getInstance().getDaoFactory().getLegaDao();
		Lega l = new Lega(idLega, 0.0f, "", "", loggato.getId());
		Lega lega = legaDao.findByPrimaryKey(l);
		if(loggato != null) {
			SquadraDao squadraDao = DatabaseManager.getInstance().getDaoFactory().getSquadraDao();
			PartitaDao partitaDao = DatabaseManager.getInstance().getDaoFactory().getPartitaDao();
			List<Squadra> squadre = squadraDao.getSquadreLega(idLega);
			request.setAttribute("lega", lega);
			request.setAttribute("squadre", squadre);
			boolean possiedoSquadra = squadraDao.utenteHaSquadraPerLega(loggato, lega);
			request.setAttribute("possiedoSquadra", possiedoSquadra);
			boolean campionatoIniziato = partitaDao.campionatoIniziato(lega);
			request.setAttribute("campionatoIniziato", campionatoIniziato);
			request.getRequestDispatcher("/WEB-INF/pagine/dettagliLega.jsp").forward(request, response);
		} else {
			Messaggio m = new Messaggio();
			m.setTitolo("Spiacente!");
			m.setTesto("Non hai i permessi necessari per visualizzare questa pagina.");
			m.setLink(request.getContextPath() + "/home");
			m.setTestoLink("Home");
			request.setAttribute("message", m);
			request.getRequestDispatcher("/WEB-INF/pagine/paginaErrore.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String descrizione = request.getParameter("descrizione");
		Long id = Long.parseLong(request.getParameter("id"));
		Utente utente = (Utente)request.getSession().getAttribute("utente");
		LegaDao legaDao = DatabaseManager.getInstance().getDaoFactory().getLegaDao();
		Lega l = new Lega(id, 0.0f, "", "", utente.getId());
		Lega lega = legaDao.findByPrimaryKey(l);
		if(utente != null && lega.getFkUtente().equals(utente.getId())) {
			lega.setDescrizione(descrizione);
			lega.setNome(nome);
			legaDao.update(lega);
			SquadraDao squadraDao = DatabaseManager.getInstance().getDaoFactory().getSquadraDao();
			List<Squadra> squadre = squadraDao.getSquadreLega(id);
			request.setAttribute("lega", lega);
			request.setAttribute("squadre", squadre);
			request.getRequestDispatcher("/WEB-INF/pagine/dettagliLega.jsp").forward(request, response);
		} else {
			Messaggio m = new Messaggio();
			m.setTitolo("Spiacente!");
			m.setTesto("Non hai i permessi necessari per visualizzare questa pagina.");
			m.setLink(request.getContextPath() + "/home");
			m.setTestoLink("Home");
			request.getRequestDispatcher("/WEB-INF/pagine/paginaErrore.jsp").forward(request, response);
		}
	}

}
