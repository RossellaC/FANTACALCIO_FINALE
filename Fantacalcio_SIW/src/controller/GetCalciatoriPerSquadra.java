package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Calciatore;
import persistence.connect.DatabaseManager;
import persistence.dao.CalciatoreDao;

/**
 * Servlet implementation class GetCalciatoriPerSquadra
 */
@WebServlet(name = "GetCalciatoriPerSquadraServlet", urlPatterns = { "/getCalciatoriPerSquadra" })
public class GetCalciatoriPerSquadra extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCalciatoriPerSquadra() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String squadra = request.getParameter("squadra");
		CalciatoreDao calciatoreDao = DatabaseManager.getInstance().getDaoFactory().getCalciatoreDAO();
		List<Calciatore> calciatori = calciatoreDao.findBySquadraDiCampionato(squadra);
		for (Calciatore c : calciatori) {
			System.out.println(c);
		}
		ObjectMapper om = new ObjectMapper();
		String result = om.writeValueAsString(calciatori);
		PrintWriter w = response.getWriter();
		w.println(result);
		w.flush();
		w.close();
	}

}
