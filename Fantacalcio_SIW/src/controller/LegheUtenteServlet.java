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
import model.Utente;
import persistence.connect.DatabaseManager;
import persistence.dao.LegaDao;

/**
 * Servlet implementation class LegheUtenteServlet
 */
@WebServlet("/leghe")
public class LegheUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LegheUtenteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		if(utente != null) {
			LegaDao legaDao = DatabaseManager.getInstance().getDaoFactory().getLegaDao();
			List<Lega> leghe = legaDao.legheUtente(utente.getId());
			request.setAttribute("leghe", leghe);
			List<Lega> leghePartecipa =legaDao.legheUtentePartecipa(utente.getId());
			request.setAttribute("leghePartecipa", leghePartecipa);
			request.getRequestDispatcher("/WEB-INF/pagine/legheUtente.jsp").forward(request, response);
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
		doGet(request, response);
	}

}
