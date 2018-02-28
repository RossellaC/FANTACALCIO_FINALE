package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Calciatore {

	private Long codice;
	private String nome;
	private String squadra;
	private Date dataNascita;
	private String ruolo;
	private double costo;

	public Calciatore() {
	}

	public Calciatore(Long codice, String nome, String squadra, Date dataNascita, String ruolo, double costo) {
		this.codice = codice;
		this.nome = nome;
		this.squadra = squadra;
		this.dataNascita = dataNascita;
		this.ruolo = ruolo;
		this.costo = costo;
	}

	public Long getCodice() {
		return codice;
	}

	public void setCodice(Long codice) {
		this.codice = codice;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSquadra() {
		return squadra;
	}

	public void setSquadra(String squadra) {
		this.squadra = squadra;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}
	
	public String getDataNascitaStringa() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(dataNascita);
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codice == null) ? 0 : codice.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Calciatore other = (Calciatore) obj;
		if (codice == null) {
			if (other.codice != null)
				return false;
		} else if (!codice.equals(other.codice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return "Calciatore " + this.getCodice() + ", " + this.getNome() + ", " + this.getSquadra() + ", "
				+ sdf.format(this.getDataNascita()) + ", " + this.getRuolo() + ", " + this.getCosto() + ", hashCode()="
				+ this.hashCode() + "]";
	}

}
