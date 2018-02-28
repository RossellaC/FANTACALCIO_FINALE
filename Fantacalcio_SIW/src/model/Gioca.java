package model;

public class Gioca {
	private Long fkCalciatore;
	private Long fkGiornata;
	private Long voto;
		
	public Gioca() {
		super();
	}

	public Gioca(Long fkCalciatore, Long fkGiornata, Long voto) {
		super();
		this.fkCalciatore = fkCalciatore;
		this.fkGiornata = fkGiornata;
		this.voto = voto;
	}

	public Long getFkCalciatore() {
		return fkCalciatore;
	}

	public void setFkCalciatore(Long fkCalciatore) {
		this.fkCalciatore = fkCalciatore;
	}

	public Long getFkGiornata() {
		return fkGiornata;
	}

	public void setFkGiornata(Long fkGiornata) {
		this.fkGiornata = fkGiornata;
	}

	public Long getVoto() {
		return voto;
	}

	public void setVoto(Long voto) {
		this.voto = voto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fkCalciatore == null) ? 0 : fkCalciatore.hashCode());
		result = prime * result + ((fkGiornata == null) ? 0 : fkGiornata.hashCode());
		result = prime * result + ((voto == null) ? 0 : voto.hashCode());
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
		Gioca other = (Gioca) obj;
		if (fkCalciatore == null) {
			if (other.fkCalciatore != null)
				return false;
		} else if (!fkCalciatore.equals(other.fkCalciatore))
			return false;
		if (fkGiornata == null) {
			if (other.fkGiornata != null)
				return false;
		} else if (!fkGiornata.equals(other.fkGiornata))
			return false;
		if (voto == null) {
			if (other.voto != null)
				return false;
		} else if (!voto.equals(other.voto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Gioca [fkCalciatore=" + fkCalciatore + ", fkGiornata=" + fkGiornata + ", voto=" + voto + "]";
	}
}
