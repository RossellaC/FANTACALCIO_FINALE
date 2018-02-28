package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Invito;
import model.Lega;
import model.Messaggio;
import model.Utente;
import persistence.connect.DatabaseManager;
import persistence.dao.InvitoDao;
import persistence.dao.LegaDao;
import persistence.dao.UtenteDao;

/**
 * Servlet implementation class InvitaUtenteServlet
 */
@WebServlet("/invitaUtente")
public class InvitaUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvitaUtenteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long idLega = Long.parseLong(request.getParameter("id"));
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		if(utente != null) {
			InvitoDao invitoDao = DatabaseManager.getInstance().getDaoFactory().getInvitoDao();
			LegaDao legaDao = DatabaseManager.getInstance().getDaoFactory().getLegaDao();
			Lega dacercare = new Lega(idLega, 0.0f, "", "", 0L);
			Lega lega = legaDao.findByPrimaryKey(dacercare); 
			request.setAttribute("lega", lega);
			List<Invito> inviti = invitoDao.getInvitiPerLega(utente.getId(), idLega);
			request.setAttribute("inviti", inviti);
			request.getRequestDispatcher("/WEB-INF/pagine/invitaUtente.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("lega"));
		String username = request.getParameter("nome");
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		InvitoDao invitoDao = DatabaseManager.getInstance().getDaoFactory().getInvitoDao();
		LegaDao legaDao = DatabaseManager.getInstance().getDaoFactory().getLegaDao();
		Lega dacercare = new Lega(id, 0.0f, "", "", 0L);
		Lega esistente = legaDao.findByPrimaryKey(dacercare);
		if(utente != null && esistente.getFkUtente().equals(utente.getId())) {
			UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
			Utente u = utenteDao.getUtente(username);
			Invito invito = new Invito(utente.getId(), u.getId(), id, false);
			invitoDao.save(invito);
			request.setAttribute("lega", esistente);
			List<Invito> inviti = invitoDao.getInvitiPerLega(utente.getId(), id);
			request.setAttribute("inviti", inviti);
			request.getRequestDispatcher("/WEB-INF/pagine/invitaUtente.jsp").forward(request, response);
		} else {
			Messaggio m = new Messaggio();
			m.setTitolo("Spiacente!");
			m.setTesto("Si &egrave; verificato un errore inaspettato.");
			m.setLink(request.getContextPath() + "/pannelloUtente");
			m.setTestoLink("Area personale");
			request.getRequestDispatcher("/WEB-INF/pagine/paginaErrore.jsp").forward(request, response);
		}
	}

}
