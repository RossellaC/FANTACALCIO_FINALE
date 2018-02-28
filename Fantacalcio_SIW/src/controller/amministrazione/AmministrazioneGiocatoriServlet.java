package controller.amministrazione;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Calciatore;
import persistence.connect.DatabaseManager;
import persistence.dao.CalciatoreDao;

/**
 * Servlet implementation class AmministrazioneGiocatoriServlet
 */
@WebServlet(AmministrazioneGiocatoriServlet.PATH)
public class AmministrazioneGiocatoriServlet extends HttpServlet {
       
	private static final long serialVersionUID = 8273702105150215419L;
	public static final String PATH = "/admin/giocatori";

	/**
     * @see HttpServlet#HttpServlet()
     */
    public AmministrazioneGiocatoriServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CalciatoreDao calciatoreDao = DatabaseManager.getInstance().getDaoFactory().getCalciatoreDAO();
		List<Calciatore> calciatori = calciatoreDao.findAllLimit(15);
		request.setAttribute("calciatori", calciatori);
		request.getRequestDispatcher("/WEB-INF/pagine/amministrazione/calciatori.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
