package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import model.Calciatore;
import model.CalciatoreJson;
import model.Utente;
import persistence.connect.DatabaseManager;
import persistence.dao.CalciatoreDao;

/**
 * Servlet implementation class RicercaCalciatoriServlet
 */
@WebServlet("/ricercaCalciatori")
public class RicercaCalciatoriServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RicercaCalciatoriServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		if (utente != null) {
			String ricerca = request.getParameter("term");
			CalciatoreDao calciatoreDao = DatabaseManager.getInstance().getDaoFactory().getCalciatoreDAO();
			if (ricerca != null && !ricerca.equals("")) {
				List<Calciatore> risultato = calciatoreDao.ricerca(ricerca);
				System.out.println("Risultato: " + risultato.size());
				List<CalciatoreJson> out = new ArrayList<>();
				for (Calciatore calciatore : risultato) {
					CalciatoreJson cj = new CalciatoreJson();
					cj.setLabel(calciatore.getNome());
					cj.setValue(calciatore.getCodice());
					out.add(cj);
				}
				System.out.println("Dim Out: " + out.size());
				ObjectMapper om = new ObjectMapper();
				om.enable(SerializationFeature.INDENT_OUTPUT);
				String res = om.writeValueAsString(out);
				System.out.println(res);
				response.getWriter().println(res);
				response.getWriter().flush();
			} else {
				List<Calciatore> risultato = calciatoreDao.findAllLimit(15);
				System.out.println("Risultato: " + risultato.size());
				List<CalciatoreJson> out = new ArrayList<>();
				for (Calciatore calciatore : risultato) {
					CalciatoreJson cj = new CalciatoreJson();
					cj.setLabel(calciatore.getNome());
					cj.setValue(calciatore.getCodice());
					out.add(cj);
				}
				ObjectMapper om = new ObjectMapper();
				om.enable(SerializationFeature.INDENT_OUTPUT);
				String res = om.writeValueAsString(out);
				System.out.println(res);
				response.getWriter().println(res);
				response.getWriter().flush();
			}
//			request.getRequestDispatcher("/WEB-INF/pezzi/risultatoRicerca.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		if (utente != null) {
			String ricerca = request.getParameter("ricerca");
			CalciatoreDao calciatoreDao = DatabaseManager.getInstance().getDaoFactory().getCalciatoreDAO();
			if (ricerca != null && !ricerca.equals("")) {
				List<Calciatore> risultato = calciatoreDao.ricerca(ricerca);
				request.setAttribute("risultato", risultato);
			} else {
				List<Calciatore> risultato = calciatoreDao.findAllLimit(15);				
				request.setAttribute("risultato", risultato);
			}
			request.getRequestDispatcher("/WEB-INF/pezzi/risultatoRicerca.jsp").forward(request, response);
		}
	}

}
