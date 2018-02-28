package model;

public class Partita {
	private Long id;
	private Long fkLega1;
	private Long fkUtente1;
	private Long fkLega2;
	private Long fkUtente2;
	private Long fkGiornata;
	private int punteggio1;
	private int punteggio2;
	private boolean avvenuta;

	public Partita() {
		super();
	}

	public Partita(Long id, Long fkLega1, Long fkUtente1, Long fkLega2, Long fkUtente2, Long fkGiornata, int punteggio1,
			int punteggio2, boolean avvenuta) {
		super();
		this.id = id;
		this.fkLega1 = fkLega1;
		this.fkUtente1 = fkUtente1;
		this.fkLega2 = fkLega2;
		this.fkUtente2 = fkUtente2;
		this.fkGiornata = fkGiornata;
		this.punteggio1 = punteggio1;
		this.punteggio2 = punteggio2;
		this.avvenuta = avvenuta;
	}

	public Long getFkLega1() {
		return fkLega1;
	}

	public void setFkLega1(Long fkLega1) {
		this.fkLega1 = fkLega1;
	}

	public Long getFkUtente1() {
		return fkUtente1;
	}

	public void setFkUtente1(Long fkUtente1) {
		this.fkUtente1 = fkUtente1;
	}

	public Long getFkLega2() {
		return fkLega2;
	}

	public void setFkLega2(Long fkLega2) {
		this.fkLega2 = fkLega2;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFkUtente2() {
		return fkUtente2;
	}

	public void setFkUtente2(Long fkUtente2) {
		this.fkUtente2 = fkUtente2;
	}

	public Long getFkGiornata() {
		return fkGiornata;
	}

	public void setFkGiornata(Long fkGiornata) {
		this.fkGiornata = fkGiornata;
	}

	public int getPunteggio1() {
		return punteggio1;
	}

	public void setPunteggio1(int punteggio1) {
		this.punteggio1 = punteggio1;
	}

	public int getPunteggio2() {
		return punteggio2;
	}

	public void setPunteggio2(int punteggio2) {
		this.punteggio2 = punteggio2;
	}

	public boolean isAvvenuta() {
		return avvenuta;
	}

	public void setAvvenuta(boolean avvenuta) {
		this.avvenuta = avvenuta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (avvenuta ? 1231 : 1237);
		result = prime * result + ((fkGiornata == null) ? 0 : fkGiornata.hashCode());
		result = prime * result + ((fkLega1 == null) ? 0 : fkLega1.hashCode());
		result = prime * result + ((fkLega2 == null) ? 0 : fkLega2.hashCode());
		result = prime * result + ((fkUtente1 == null) ? 0 : fkUtente1.hashCode());
		result = prime * result + ((fkUtente2 == null) ? 0 : fkUtente2.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + punteggio1;
		result = prime * result + punteggio2;
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
		Partita other = (Partita) obj;
		if (avvenuta != other.avvenuta)
			return false;
		if (fkGiornata == null) {
			if (other.fkGiornata != null)
				return false;
		} else if (!fkGiornata.equals(other.fkGiornata))
			return false;
		if (fkLega1 == null) {
			if (other.fkLega1 != null)
				return false;
		} else if (!fkLega1.equals(other.fkLega1))
			return false;
		if (fkLega2 == null) {
			if (other.fkLega2 != null)
				return false;
		} else if (!fkLega2.equals(other.fkLega2))
			return false;
		if (fkUtente1 == null) {
			if (other.fkUtente1 != null)
				return false;
		} else if (!fkUtente1.equals(other.fkUtente1))
			return false;
		if (fkUtente2 == null) {
			if (other.fkUtente2 != null)
				return false;
		} else if (!fkUtente2.equals(other.fkUtente2))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (punteggio1 != other.punteggio1)
			return false;
		if (punteggio2 != other.punteggio2)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Partita [id=" + id + ", fkLega1=" + fkLega1 + ", fkUtente1=" + fkUtente1 + ", fkLega2=" + fkLega2
				+ ", fkUtente2=" + fkUtente2 + ", fkGiornata=" + fkGiornata + ", punteggio1=" + punteggio1
				+ ", punteggio2=" + punteggio2 + ", avvenuta=" + avvenuta + "]";
	}
}
