package controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import forms.FormRegistrazione;
import model.Messaggio;
import model.Utente;
import persistence.connect.DatabaseManager;
import persistence.connect.PersistenceException;
import persistence.dao.UtenteDao;

/**
 * Servlet implementation class IscrizioneUtente
 */
@WebServlet("/registrazione")
public class IscrizioneUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IscrizioneUtente() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		FormRegistrazione form = new FormRegistrazione();
		Messaggio m = new Messaggio();
		request.setAttribute("form", form);
		request.setAttribute("messaggio", m);
		request.getRequestDispatcher("/WEB-INF/pagine/registrazione.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FormRegistrazione form = new FormRegistrazione();
		UtenteDao utenteDao = DatabaseManager.getInstance().getDaoFactory().getUtenteDao();
		try {
			BeanUtils.populate(form, request.getParameterMap());
			Utente ut = new Utente(0L, form.getUsername(), form.getPassword(), form.getNome(), form.getCognome(), form.getEmail());
			utenteDao.save(ut);
			Messaggio m = new Messaggio();
			m.setTitolo("Ottimo");
			m.setTesto("La regitrazione &egrave; andata a buon fine.");
			m.setLink(request.getContextPath() + "/index");
			m.setTestoLink("Home");
			request.setAttribute("message", m);
			System.out.println(ut+" - "+ut.getNome());
			Utente utSalvato = utenteDao.getUtente(form.getUsername());
			request.getSession().setAttribute("utente" ,utSalvato);
			request.getSession().setAttribute("username", ut.getNome());
			request.getRequestDispatcher("/WEB-INF/pagine/paginaErrore.jsp").forward(request, response);
		} catch (IllegalAccessException | InvocationTargetException e) {
			Messaggio m = new Messaggio();
			m.setTitolo("Spiacente!");
			m.setTesto("La regitrazione non &egrave; andata a buon fine. Riprova pi&ugrave; tardi.");
			m.setLink(request.getContextPath() + "/index");
			m.setTestoLink("Home");
			request.setAttribute("message", m);
			request.setAttribute("form", form);
			request.getRequestDispatcher("/WEB-INF/pagine/registrazione.jsp").forward(request, response);			
		} catch (PersistenceException ex) {
			Messaggio m = new Messaggio();
			m.setTesto("La regitrazione non &egrave; andata a buon fine. Potresti essere gi&agrave; registrato?");
			request.setAttribute("message", m);
			request.setAttribute("form", form);
			request.getRequestDispatcher("/WEB-INF/pagine/registrazione.jsp").forward(request, response);			
		}
	}
}
