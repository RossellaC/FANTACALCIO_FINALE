package model;

public class Invito {

	private Long fkUtenteInvia;
	private Long fkUtenteRiceve;
	private Long fkLega;
	private boolean accettazione;

	public Invito() {
		super();
	}

	public Invito(Long fkUtenteInvia, Long fkUtenteRiceve, Long fkLega, boolean accettazione) {
		super();
		this.fkUtenteInvia = fkUtenteInvia;
		this.fkUtenteRiceve = fkUtenteRiceve;
		this.fkLega = fkLega;
		this.accettazione = accettazione;
	}

	public Long getFkUtenteInvia() {
		return fkUtenteInvia;
	}

	public void setFkUtenteInvia(Long fkUtenteInvia) {
		this.fkUtenteInvia = fkUtenteInvia;
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
		result = prime * result + ((fkUtenteInvia == null) ? 0 : fkUtenteInvia.hashCode());
		result = prime * result + ((fkUtenteRiceve == null) ? 0 : fkUtenteRiceve.hashCode());
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
		Invito other = (Invito) obj;
		if (accettazione != other.accettazione)
			return false;
		if (fkLega == null) {
			if (other.fkLega != null)
				return false;
		} else if (!fkLega.equals(other.fkLega))
			return false;
		if (fkUtenteInvia == null) {
			if (other.fkUtenteInvia != null)
				return false;
		} else if (!fkUtenteInvia.equals(other.fkUtenteInvia))
			return false;
		if (fkUtenteRiceve == null) {
			if (other.fkUtenteRiceve != null)
				return false;
		} else if (!fkUtenteRiceve.equals(other.fkUtenteRiceve))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Invito [fkUtenteInvia=" + fkUtenteInvia + ", fkUtenteRiceve=" + fkUtenteRiceve + ", fkLega=" + fkLega
				+ ", accettazione=" + accettazione + "]";
	}
}
