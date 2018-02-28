package model;

public class GiocaCalciatore {
	private String squadra;
	private String nome;
	private Long voto;

	public GiocaCalciatore() {
		super();
	}

	public GiocaCalciatore(String squadra, String nome, Long voto) {
		super();
		this.squadra = squadra;
		this.nome = nome;
		this.voto = voto;
	}

	public String getSquadra() {
		return squadra;
	}

	public void setSquadra(String squadra) {
		this.squadra = squadra;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getVoto() {
		return voto;
	}

	public void setVoto(Long voto) {
		this.voto = voto;
	}

	@Override
	public String toString() {
		return "GiocaCalciatore [squadra=" + squadra + ", nome=" + nome + ", voto=" + voto + "]";
	}
}
