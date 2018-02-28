package model;

import javax.servlet.http.HttpServletRequest;

public class Messaggio {
	
	public static final int TIPO_SUCCESSO = 1;
	public static final int TIPO_AVVISO = 2;
	public static final int TIPO_ERRORE = 3;
	public static final int TIPO_INFO = 4;
	
	private boolean attivo;
	private int tipo;
	private String titolo;
	private String testo;
	private String link;
	private String testoLink;
	
	public Messaggio() {
		super();
	}
	
	public boolean isAttivo() {
		return attivo;
	}

	public void setAttivo(boolean attivo) {
		this.attivo = attivo;
	}
	
	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getTesto() {
		return testo;
	}
	public void setTesto(String testo) {
		this.testo = testo;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getTestoLink() {
		return testoLink;
	}
	public void setTestoLink(String testoLink) {
		this.testoLink = testoLink;
	}
	
	public static void setup(HttpServletRequest request) {
		Messaggio m = (Messaggio) request.getSession().getAttribute("message");
		if(m == null) {
			m = new Messaggio();
			m.setAttivo(false);
		}
		request.getSession().setAttribute("message", null);
		request.setAttribute("message", m);
	}
}
