package forms;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormGiornata {

	public static final String DATE_FORMAT = "dd/MM/yyyy";
	public static final String DATE_FORMAT_PARSE = "yyyy-MM-dd";
	
	private Date inizio;
	private Date fine;

	public FormGiornata() {
		super();
	}

	public Date getInizio() {
		return inizio;
	}

	public void setInizio(Date inizio) {
		this.inizio = inizio;
	}

	public Date getFine() {
		return fine;
	}

	public void setFine(Date fine) {
		this.fine = fine;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_PARSE);
		return "FormGiornata [inizio=" + sdf.format(inizio) + ", fine=" + sdf.format(fine) + "]";
	}
}
