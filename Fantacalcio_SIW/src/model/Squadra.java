package model;

public class Squadra {

	private Long fkLega;
	private Long fkUtente;
	private String nome;
	private int punteggio;

	public Squadra() {
		super();
	}
	
	public Squadra(Long fkLega, Long fkUtente, String nome) {
		super();
		this.fkLega = fkLega;
		this.fkUtente = fkUtente;
		this.nome = nome;
	}

	public Squadra(Long fkLega, Long fkUtente, String nome,int punteggio) {
		super();
		this.fkLega = fkLega;
		this.fkUtente = fkUtente;
		this.nome = nome;
		this.punteggio = punteggio;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}
	
	public int getPunteggio() {
		return punteggio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fkLega == null) ? 0 : fkLega.hashCode());
		result = prime * result + ((fkUtente == null) ? 0 : fkUtente.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Squadra other = (Squadra) obj;
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
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Squadra [fkLega=" + fkLega + ", fkUtente=" + fkUtente + ", nome=" + nome +" ,punteggio= "+punteggio+"]";
	}
}
