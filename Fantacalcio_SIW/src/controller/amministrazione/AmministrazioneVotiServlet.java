package controller.amministrazione;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import controller.RisultatoRichiestaServlet;
import forms.FormVoto;
import model.Composta;
import model.Gioca;
import model.GiocaCalciatore;
import model.Giornata;
import model.Messaggio;
import model.Squadra;
import persistence.connect.DatabaseManager;
import persistence.connect.PersistenceException;
import persistence.dao.CalciatoreDao;
import persistence.dao.CompostaDao;
import persistence.dao.GiocaDao;
import persistence.dao.GiornataDao;
import persistence.dao.SquadraDao;

/**
 * Servlet implementation class AmministrazioneVotiServlet
 */
@WebServlet(AmministrazioneVotiServlet.PATH)
public class AmministrazioneVotiServlet extends HttpServlet {

	private static final long serialVersionUID = 8538817562112705932L;
	public static final String PATH = "/admin/voti";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AmministrazioneVotiServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Long idGiornata = Long.parseLong(request.getParameter("giornata"));
			request.getSession().setAttribute("idGiornata", idGiornata);
			System.out.println("id giornata servlet " + idGiornata);
			Messaggio.setup(request);
			CalciatoreDao calciatoreDao = DatabaseManager.getInstance().getDaoFactory().getCalciatoreDAO();
			GiornataDao giornataDao = DatabaseManager.getInstance().getDaoFactory().getGiornataDAO();
			GiocaDao giocaDao = DatabaseManager.getInstance().getDaoFactory().getGiocaDao();
			Giornata giornata = giornataDao.findByPrimaryKey(idGiornata);
			List<String> squadre = calciatoreDao.getAllSquadre();
			List<GiocaCalciatore> giocaCalciatori = giocaDao.getGiocaPerGiornata(idGiornata);
			request.setAttribute("squadre", squadre);
			request.setAttribute("giornata", giornata);
			request.setAttribute("giocaCalciatori", giocaCalciatori);
			request.getRequestDispatcher("/WEB-INF/pagine/amministrazione/voti.jsp").forward(request, response);
		} catch (NumberFormatException e) {
//			Messaggio m = new Messaggio();
//			m.setAttivo(true);
//			m.setTipo(Messaggio.TIPO_ERRORE);
//			m.setTitolo("Errore!");
//			m.setTesto("La giornata non &egrave; stata indicata nel modo corretto.");
//			m.setTestoLink("Torna alle giornate");
//			m.setLink(request.getContextPath() + "/admin/giornate");
//			request.getSession().setAttribute("message", m);
			response.sendRedirect(request.getContextPath() + RisultatoRichiestaServlet.PATH);
		} catch (PersistenceException pe) {
//			Messaggio m = new Messaggio();
//			m.setAttivo(true);
//			m.setTipo(Messaggio.TIPO_ERRORE);
//			m.setTitolo("Errore!");
//			m.setTesto(pe.getMessage());
//			m.setTestoLink("Torna alle giornate.");
//			m.setLink(request.getContextPath() + "/admin/giornate");
//			request.getSession().setAttribute("message", m);
			response.sendRedirect(request.getContextPath() + RisultatoRichiestaServlet.PATH);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FormVoto form = new FormVoto();
		try {
			BeanUtils.populate(form, request.getParameterMap());
			Gioca gioca = new Gioca(form.getGiocatore(), form.getGiornata(), form.getVoto());
			GiocaDao giocaDao = DatabaseManager.getInstance().getDaoFactory().getGiocaDao();
			giocaDao.save(gioca);
			
			SquadraDao squadraDao = DatabaseManager.getInstance().getDaoFactory().getSquadraDao();
			
			CompostaDao compostaDao = DatabaseManager.getInstance().getDaoFactory().getCompostaDao();
			
			for(Composta c : compostaDao.getAllComposta()) {
				System.out.println(c);
				Squadra l = new Squadra();
				l.setFkLega(c.getFkLega());
				l.setFkUtente(c.getFkUtente());
				if(c.getFkCalciatore().equals(form.getGiocatore())) {
					
					int punteggio = squadraDao.findByPrimaryKey(l).getPunteggio();
					punteggio+=form.getVoto();
				
					squadraDao.updatePunteggio(l, punteggio);
				}
			}
			
			request.getSession().setAttribute("idGiornata", null);
			Messaggio m = new Messaggio();
			m.setAttivo(true);
			m.setTipo(Messaggio.TIPO_SUCCESSO);
			m.setTitolo("Ottimo!");
			m.setTesto("Il voto &grave; stato inserito con successo.");
			m.setTestoLink("Pagina voti");
			m.setLink(request.getContextPath() + AmministrazioneVotiServlet.PATH+"?giornata="+form.getGiornata());
			System.out.println(request.getContextPath() + AmministrazioneVotiServlet.PATH+"?giornata="+form.getGiornata());
			request.getSession().setAttribute("message", m);
			response.sendRedirect(request.getContextPath() + AmministrazioneVotiServlet.PATH);
		} catch (IllegalAccessException | InvocationTargetException e) {
			Messaggio m = new Messaggio();
			m.setAttivo(true);
			m.setTipo(Messaggio.TIPO_ERRORE);
			m.setTitolo("Errore!");
			m.setTesto("I dati relativi al voto non sono stati indicati correttamente.");
			m.setTestoLink("");
			System.out.println(request.getContextPath() + AmministrazioneVotiServlet.PATH+"?giornata="+form.getGiornata());
			m.setLink(request.getContextPath() + AmministrazioneVotiServlet.PATH+"?giornata="+form.getGiornata());
			request.getSession().setAttribute("message", m);
			response.sendRedirect(request.getContextPath() + AmministrazioneVotiServlet.PATH);
		} catch (PersistenceException pe) {
			Messaggio m = new Messaggio();
			m.setAttivo(true);
			m.setTipo(Messaggio.TIPO_ERRORE);
			m.setTitolo("Errore!");
			m.setTesto("I dati relativi al voto per il giocatore scelto &egrave; stato gi&agrave; inserito..");
			m.setTestoLink("");
			m.setLink("");
			request.getSession().setAttribute("message", m);
			Long idGiornata = (Long) request.getSession().getAttribute("idGiornata");
			response.sendRedirect(
					request.getContextPath() + AmministrazioneVotiServlet.PATH + "?giornata=" + idGiornata);
		}
	}

}
