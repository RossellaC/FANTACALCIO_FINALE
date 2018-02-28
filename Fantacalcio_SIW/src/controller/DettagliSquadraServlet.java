package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Calciatore;
import model.Squadra;
import persistence.connect.DatabaseManager;
import persistence.dao.CalciatoreDao;
import persistence.dao.SquadraDao;

/**
 * Servlet implementation class DettagliSquadraServlet
 */
@WebServlet("/dettagliSquadra")
public class DettagliSquadraServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DettagliSquadraServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idLega = request.getParameter("lega");
		String utente = request.getParameter("utente");
		SquadraDao squadraDao = DatabaseManager.getInstance().getDaoFactory().getSquadraDao();
		Squadra s = new Squadra(Long.parseLong(idLega), Long.parseLong(utente), "");
		Squadra squadra = squadraDao.findByPrimaryKey(s);
		CalciatoreDao calciatoreDao = DatabaseManager.getInstance().getDaoFactory().getCalciatoreDAO();
		List<Calciatore> calciatori = calciatoreDao.findBySquadra(s);
		request.setAttribute("squadra", squadra);
		request.setAttribute("calciatori", calciatori);
		request.getRequestDispatcher("/WEB-INF/pagine/dettagliSquadra.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
