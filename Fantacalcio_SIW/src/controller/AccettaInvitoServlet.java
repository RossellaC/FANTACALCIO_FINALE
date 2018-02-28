package controller;

import java.io.IOException; 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Invito;
import model.Utente;
import persistence.connect.DatabaseManager;
import persistence.dao.InvitoDao;

/**
 * Servlet implementation class AccettaInvitoServlet
 */
@WebServlet("/accettaInvito")
public class AccettaInvitoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccettaInvitoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long idLega = Long.parseLong(request.getParameter("idLega"));
		Utente utente = (Utente) request.getSession().getAttribute("utente");
		if(utente != null) {
			InvitoDao invitoDao = DatabaseManager.getInstance().getDaoFactory().getInvitoDao();
			Invito invito = new Invito(0L, utente.getId(), idLega, true);
			invitoDao.accettaInvito(invito);
			response.sendRedirect(request.getContextPath() + "/pannelloUtente");
		}
	}

}
