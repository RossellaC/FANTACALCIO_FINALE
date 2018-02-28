package model;

public class Lega {
	private long id;
	private float budgetIniziale;
	private String nome;
	private String descrizione;
	private Long fkUtente;

	public Lega() {
		super();
	}

	public Lega(long id, float budgetIniziale, String nome, String descrizione, Long fkUtente) {
		super();
		this.id = id;
		this.budgetIniziale = budgetIniziale;
		this.nome = nome;
		this.descrizione = descrizione;
		this.fkUtente = fkUtente;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getBudgetIniziale() {
		return budgetIniziale;
	}

	public void setBudgetIniziale(float budgetIniziale) {
		this.budgetIniziale = budgetIniziale;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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
		result = prime * result + Float.floatToIntBits(budgetIniziale);
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + ((fkUtente == null) ? 0 : fkUtente.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Lega other = (Lega) obj;
		if (Float.floatToIntBits(budgetIniziale) != Float.floatToIntBits(other.budgetIniziale))
			return false;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (fkUtente == null) {
			if (other.fkUtente != null)
				return false;
		} else if (!fkUtente.equals(other.fkUtente))
			return false;
		if (id != other.id)
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
		return "Lega [id=" + id + ", budgetIniziale=" + budgetIniziale + ", nome=" + nome + ", descrizione="
				+ descrizione + ", fkUtente=" + fkUtente + "]";
	}
}
