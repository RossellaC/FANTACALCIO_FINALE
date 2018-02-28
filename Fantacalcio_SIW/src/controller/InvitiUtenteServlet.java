package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.InvitoLega;
import model.Messaggio;
import model.Utente;
import persistence.connect.DatabaseManager;
import persistence.dao.InvitoDao;

/**
 * Servlet implementation class InvitiUtenteServlet
 */
@WebServlet("/inviti")
public class InvitiUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvitiUtenteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		if(utente != null) {
			InvitoDao invitoDao = DatabaseManager.getInstance().getDaoFactory().getInvitoDao();
			List<InvitoLega> inviti = invitoDao.getInvitiRicevutiUtente(utente.getId());
			request.setAttribute("inviti", inviti);
			List<InvitoLega> invitiSenzaSquadra = invitoDao.invitiSenzaSquadra(utente.getId());
			request.setAttribute("accettati", invitiSenzaSquadra);
			request.getRequestDispatcher("/WEB-INF/pagine/inviti.jsp").forward(request, response);
		} else {
			Messaggio m = new Messaggio();
			m.setTitolo("Spiacente!");
			m.setTesto("Non hai i permessi necessari per visualizzare questa pagina.");
			m.setLink(request.getContextPath() + "/home");
			m.setTestoLink("Home");
			request.getRequestDispatcher("/WEB-INF/pagine/paginaErrore.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Gestione dell'accettazione di un invito con inoltro alla pagina di creazione di una squadra.
	}

}
