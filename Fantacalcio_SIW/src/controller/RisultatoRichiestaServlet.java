package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Messaggio;

/**
 * Servlet implementation class RisultatoRichiestaServlet
 */
@WebServlet(RisultatoRichiestaServlet.PATH)
public class RisultatoRichiestaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String PATH = "/risultato";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RisultatoRichiestaServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Messaggio m = (Messaggio)request.getSession().getAttribute("message");
		request.getSession().setAttribute("message", null);
		request.setAttribute("message", m);
		request.getRequestDispatcher("/WEB-INF/pagine/paginaErrore.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
