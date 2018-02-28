package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Giornata {
	private Long id;
	private Date dataInizio;
	private Date dataFine;

	public Giornata() {
		super();
	}

	public Giornata(Long id, Date dataInizio, Date dataFine) {
		super();
		this.id = id;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy");
		return "Giornata [id=" + id + ", dataInizio=" + sdf.format(dataInizio) + ", dataFine=" + sdf.format(dataFine) + "]";
	}	
}
