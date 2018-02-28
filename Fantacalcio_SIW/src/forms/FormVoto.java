package forms;

public class FormVoto {
	private Long giornata;
	private Long giocatore;
	private Long voto;

	public FormVoto() {
		super();
	}

	public FormVoto(Long giornata, Long giocatore, Long voto) {
		super();
		this.giornata = giornata;
		this.giocatore = giocatore;
		this.voto = voto;
	}

	public Long getGiornata() {
		return giornata;
	}

	public void setGiornata(Long giornata) {
		this.giornata = giornata;
	}

	public Long getGiocatore() {
		return giocatore;
	}

	public void setGiocatore(Long giocatore) {
		this.giocatore = giocatore;
	}

	public Long getVoto() {
		return voto;
	}

	public void setVoto(Long voto) {
		this.voto = voto;
	}

	@Override
	public String toString() {
		return "FormVoto [giornata=" + giornata + ", giocatore=" + giocatore + ", voto=" + voto + "]";
	}
}
