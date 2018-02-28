package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Messaggio;
import model.Utente;
import persistence.connect.DatabaseManager;
import persistence.dao.UtenteDao;

/**
 * Servlet implementation class AccediServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.getRequestDispatcher("/WEB-INF/pagine/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");		
		System.out.println("Username: " + username);
		System.out.println("Password: " + password);
		
		UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
		Utente esistente=utenteDao.getUtente(username);
		
		System.out.println(esistente);
		if(esistente != null && esistente.getPassword() != null && esistente.getPassword().equals(password)) {
			request.getSession().setAttribute("utente" ,esistente);
			request.getSession().setAttribute("username", esistente.getNome());
			response.sendRedirect(request.getContextPath() + "/pannelloUtente");
		} else {
			Messaggio m = new Messaggio();
			m.setTitolo("Utente non presente");
			m.setTestoLink("login");
			m.setLink(request.getContextPath()+"/login");
			m.setTesto("L'utente non è registrato");
			
			request.setAttribute("message", m);
			request.getRequestDispatcher("/WEB-INF/pagine/paginaErrore.jsp").forward(request, response);
		}
	}

}
