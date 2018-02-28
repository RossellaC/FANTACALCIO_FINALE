package forms;

import java.util.List;

public class CreaSquadra {
	
	private Long lega;
	private String nomeSquadra;
	private List<Long> giocatori;

	public CreaSquadra() {
		super();
	}
	
	public Long getLega() {
		return lega;
	}

	public void setLega(Long lega) {
		this.lega = lega;
	}

	public String getNomeSquadra() {
		return nomeSquadra;
	}

	public void setNomeSquadra(String nome) {
		this.nomeSquadra = nome;
	}

	public List<Long> getGiocatori() {
		return giocatori;
	}

	public void setGiocatori(List<Long> giocatori) {
		this.giocatori = giocatori;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CreaSquadra {nome=");
		builder.append(nomeSquadra);
		builder.append(", lega=").append(lega);
		builder.append(", giocatori={");
		for(Long g : this.giocatori) {
			builder.append(g + ",");			
		}
		builder.deleteCharAt(builder.length()-1);
		builder.append("}");
		builder.append("}");
		return builder.toString();
	}
}
