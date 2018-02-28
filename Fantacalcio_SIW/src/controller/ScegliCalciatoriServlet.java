package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Calciatore;
import persistence.connect.DAOFactory;
import persistence.dao.CalciatoreDao;

/**
 * Servlet implementation class ScegliCalciatoriServlet
 */
@WebServlet("/scegliCalciatori")
public class ScegliCalciatoriServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScegliCalciatoriServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory factory = DAOFactory.getDAOFactory(DAOFactory.POSTGRESQL);
		CalciatoreDao cd = factory.getCalciatoreDAO();
		List<Calciatore> calciatori = cd.findAll();
		request.setAttribute("calciatori", calciatori);
		request.getRequestDispatcher("/WEB-INF/pagine/scegliCalciatori.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
