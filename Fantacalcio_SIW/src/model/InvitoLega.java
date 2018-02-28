package model;

public class InvitoLega {
	
	private String mittente;
	private Long fkUtenteRiceve;
	private Long fkLega;
	private String nomeLega;
	private boolean accettazione;

	public InvitoLega() {
		super();
	}

	public InvitoLega(String mittente, Long fkUtenteRiceve, Long fkLega, String nomeLega, boolean accettazione) {
		super();
		this.mittente = mittente;
		this.fkUtenteRiceve = fkUtenteRiceve;
		this.fkLega = fkLega;
		this.nomeLega = nomeLega;
		this.accettazione = accettazione;
	}

	public String getMittente() {
		return mittente;
	}

	public void setMittente(String mittente) {
		this.mittente = mittente;
	}

	public Long getFkUtenteRiceve() {
		return fkUtenteRiceve;
	}

	public void setFkUtenteRiceve(Long fkUtenteRiceve) {
		this.fkUtenteRiceve = fkUtenteRiceve;
	}

	public Long getFkLega() {
		return fkLega;
	}

	public void setFkLega(Long fkLega) {
		this.fkLega = fkLega;
	}

	public String getNomeLega() {
		return nomeLega;
	}

	public void setNomeLega(String nomeLega) {
		this.nomeLega = nomeLega;
	}

	public boolean isAccettazione() {
		return accettazione;
	}

	public void setAccettazione(boolean accettazione) {
		this.accettazione = accettazione;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (accettazione ? 1231 : 1237);
		result = prime * result + ((fkLega == null) ? 0 : fkLega.hashCode());
		result = prime * result + ((fkUtenteRiceve == null) ? 0 : fkUtenteRiceve.hashCode());
		result = prime * result + ((mittente == null) ? 0 : mittente.hashCode());
		result = prime * result + ((nomeLega == null) ? 0 : nomeLega.hashCode());
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
		InvitoLega other = (InvitoLega) obj;
		if (accettazione != other.accettazione)
			return false;
		if (fkLega == null) {
			if (other.fkLega != null)
				return false;
		} else if (!fkLega.equals(other.fkLega))
			return false;
		if (fkUtenteRiceve == null) {
			if (other.fkUtenteRiceve != null)
				return false;
		} else if (!fkUtenteRiceve.equals(other.fkUtenteRiceve))
			return false;
		if (mittente == null) {
			if (other.mittente != null)
				return false;
		} else if (!mittente.equals(other.mittente))
			return false;
		if (nomeLega == null) {
			if (other.nomeLega != null)
				return false;
		} else if (!nomeLega.equals(other.nomeLega))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InvitoLega [mittente=" + mittente + ", fkUtenteRiceve=" + fkUtenteRiceve + ", fkLega=" + fkLega
				+ ", nomeLega=" + nomeLega + ", accettazione=" + accettazione + "]";
	}
}
