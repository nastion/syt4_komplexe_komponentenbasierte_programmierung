package pfuchs.syt4.westbahn.model;

import javax.persistence.*;

@MappedSuperclass
public abstract class Ticket {
	@Id
	@GeneratedValue
    Long ID;

	@OneToOne
    private Strecke strecke;
	
	@Embedded
    private Zahlung zahlung;

	@Embedded
    private TicketOption DTYPE;
	
	public Ticket() {}
	
	public Ticket(Strecke strecke, Zahlung zahlung) {
		this.strecke = strecke;
		this.zahlung = zahlung;
	}

	public abstract String print();
}
