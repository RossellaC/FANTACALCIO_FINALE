package controller.amministrazione;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import forms.FormGiornata;
import model.Giornata;
import model.Messaggio;
import persistence.connect.DatabaseManager;
import persistence.connect.PersistenceException;
import persistence.dao.GiornataDao;

/**
 * Servlet implementation class AmministrazioneGiornateServlet
 */
@WebServlet(AmministrazioneGiornateServlet.PATH)
public class AmministrazioneGiornateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String PATH = "/admin/giornate";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AmministrazioneGiornateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GiornataDao giornataDao = DatabaseManager.getInstance().getDaoFactory().getGiornataDAO();
		List<Giornata> giornate = giornataDao.getAllGiornate();
		Messaggio.setup(request);
		request.setAttribute("giornate", giornate);
		request.getRequestDispatcher("/WEB-INF/pagine/amministrazione/giornate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FormGiornata form = new FormGiornata();
		System.out.println(request.getParameter("inizio"));
		System.out.println(request.getParameter("fine"));
		try {
			DateConverter conv = new DateConverter(null);
            conv.setPattern(FormGiornata.DATE_FORMAT_PARSE);
            ConvertUtils.register(conv, Date.class);
            BeanUtils.populate(form, request.getParameterMap());
			GiornataDao giornataDao = DatabaseManager.getInstance().getDaoFactory().getGiornataDAO();
			Giornata g = new Giornata(0L, form.getInizio(), form.getFine());
			giornataDao.save(g);
			Messaggio m = new Messaggio();
			m.setAttivo(true);
			m.setTitolo("Fatto!");
			m.setTesto("La giornata di campionato &egrave; stata aggiunta con successo.");
			m.setLink(request.getContextPath() + "/admin/giornate");
			m.setTestoLink("Aggiungi altre giornate.");
			request.getSession().setAttribute("message", m);
			response.sendRedirect(request.getContextPath() + "/admin/giornate");
		} catch (IllegalAccessException | InvocationTargetException e) {
			Messaggio m = new Messaggio();
			m.setAttivo(true);
			m.setTitolo("Spiacente!");
			m.setTesto("La giornata di campionato non &egrave; stata aggiunta! I dati inseriti non sono corretti.");
			m.setLink(request.getContextPath() + "/admin/giornate");
			m.setTestoLink("Riprova");
			request.setAttribute("message", m);
			request.getRequestDispatcher("/WEB-INF/pagine/amministrazione/giornate.jsp").forward(request, response);
		} catch (PersistenceException pe) {
			Messaggio m = new Messaggio();
			m.setAttivo(true);
			m.setTitolo("Spiacente!");
			m.setTesto("La giornata di campionato non &egrave; stata aggiunta! I dati inseriti non sono corretti.");
			m.setLink(request.getContextPath() + "/admin/giornate");
			m.setTestoLink("Riprova");
			request.setAttribute("message", m);
			request.getRequestDispatcher("/WEB-INF/pagine/amministrazione/giornate.jsp").forward(request, response);
		}
	}
}
