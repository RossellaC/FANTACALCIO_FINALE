package controller;

import java.io.IOException;
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
 * Servlet implementation class DatiCalciatoreServlet
 */
@WebServlet("/datiCalciatore")
public class DatiCalciatoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatiCalciatoreServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String s = request.getParameter("cod");
		CalciatoreDao calciatoreDao = DatabaseManager.getInstance().getDaoFactory().getCalciatoreDAO();
		Calciatore c = calciatoreDao.findByPrimaryKey(Long.parseLong(s));
		if(c != null) {
			ObjectMapper om = new ObjectMapper();
			String sc = om.writeValueAsString(c);
			
			response.getWriter().println(sc);
			response.getWriter().flush();
		} else {
			response.getWriter().println("");
			response.getWriter().flush();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
