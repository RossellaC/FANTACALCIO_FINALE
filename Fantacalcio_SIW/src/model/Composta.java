package model;

public class Composta {

	private Long fkCalciatore;
	private Long fkLega;
	private Long fkUtente;

	public Composta() {
		super();
	}

	public Composta(Long fkCalciatore, Long fkLega, Long fkUtente) {
		super();
		this.fkCalciatore = fkCalciatore;
		this.fkLega = fkLega;
		this.fkUtente = fkUtente;
	}

	public Long getFkCalciatore() {
		return fkCalciatore;
	}

	public void setFkCalciatore(Long fkCalciatore) {
		this.fkCalciatore = fkCalciatore;
	}

	public Long getFkLega() {
		return fkLega;
	}

	public void setFkLega(Long fkLega) {
		this.fkLega = fkLega;
	}

	public Long getFkUtente() {
		return fkUtente;
	}

	public void setFkUtente(Long fkUtente) {
		this.fkUtente = fkUtente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fkCalciatore == null) ? 0 : fkCalciatore.hashCode());
		result = prime * result + ((fkLega == null) ? 0 : fkLega.hashCode());
		result = prime * result + ((fkUtente == null) ? 0 : fkUtente.hashCode());
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
		Composta other = (Composta) obj;
		if (fkCalciatore == null) {
			if (other.fkCalciatore != null)
				return false;
		} else if (!fkCalciatore.equals(other.fkCalciatore))
			return false;
		if (fkLega == null) {
			if (other.fkLega != null)
				return false;
		} else if (!fkLega.equals(other.fkLega))
			return false;
		if (fkUtente == null) {
			if (other.fkUtente != null)
				return false;
		} else if (!fkUtente.equals(other.fkUtente))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Composta [fkCalciatore=" + fkCalciatore + ", fkLega=" + fkLega + ", fkUtente=" + fkUtente + "]";
	}
}
