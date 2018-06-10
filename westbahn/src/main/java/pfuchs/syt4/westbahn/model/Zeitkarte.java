package pfuchs.syt4.westbahn.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Zeitkarte extends Ticket implements Serializable {
	private Date gueltigAb;

	@Enumerated
	private ZeitkartenTyp typ;
	
	public Zeitkarte() {}
	
	public Zeitkarte(ZeitkartenTyp typ, Date gueltigAb, Strecke strecke, Zahlung zahlung) {
		super(strecke,zahlung);
		this.typ = typ;
		this.gueltigAb = gueltigAb;
	}

	public ZeitkartenTyp getTyp() {
		return typ;
	}
	public void setTyp(ZeitkartenTyp typ) {
	    this.typ = typ;
	}

	@Override
	public String print() {
		return "Ticket mit ID " + super.Id + " ist gültig ab " + gueltigAb.toString() + " und ist eine " + typ;
	}

}
