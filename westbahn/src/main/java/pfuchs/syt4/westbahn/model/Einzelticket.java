package pfuchs.syt4.westbahn.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Einzelticket extends Ticket {
	@Enumerated(EnumType.ORDINAL)
	private TicketOption ticketOption;

	private int anzahlPers;

	public Einzelticket() {}
	public Einzelticket(TicketOption ticketOption, Strecke strecke, Zahlung zahlung) {
		super(strecke, zahlung);
		this.ticketOption = ticketOption;
	}

    public TicketOption getTicketOption() {
        return ticketOption;
    }

    public void setTicketOption(TicketOption ticketOption) {
        this.ticketOption = ticketOption;
    }

    @Override
	public String print() {
		return "Ticket " +super.Id + " ist ein Einzelticket mit der Option " + ticketOption;
	}

    public int getAnzahlPers() {
        return anzahlPers;
    }

    public void setAnzahlPers(int anzahlPers) {
        this.anzahlPers = anzahlPers;
    }

    @Override
    public double preis() {
	    double preis = getStrecke().getEnde().getAbsZeitEntfernung()-getStrecke().getStart().getAbsPreisEntfernung();
	    if (ticketOption != null && ticketOption.equals(TicketOption.FAHRRAD))
	        return preis*this.getAnzahlPers()*Preisstaffelung.getInstance().getFahrrad();
	    else if (ticketOption != null && ticketOption.equals(TicketOption.GROSSGEPAECK)) {
	        return preis*this.getAnzahlPers()*Preisstaffelung.getInstance().getGrossGepaeck();
        } else return preis*this.getAnzahlPers();
    }
}
