package pfuchs.syt4.westbahn.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Einzelticket extends Ticket {
	@Enumerated(EnumType.ORDINAL)
	private TicketOption ticketOption;

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

}
