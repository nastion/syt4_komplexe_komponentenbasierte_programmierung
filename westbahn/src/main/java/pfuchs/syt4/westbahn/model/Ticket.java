package pfuchs.syt4.westbahn.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Ticket {
	@Id
    @GeneratedValue
    Long Id;

	@OneToOne
    private Strecke strecke;
	
	@Embedded
    private Zahlung zahlung;
	
	public Ticket() {}
	
	public Ticket(Strecke strecke, Zahlung zahlung) {
		this.strecke = strecke;
		this.zahlung = zahlung;
	}

	public abstract String print();

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Strecke getStrecke() {
        return strecke;
    }

    public void setStrecke(Strecke strecke) {
        this.strecke = strecke;
    }

    public Zahlung getZahlung() {
        return zahlung;
    }

    public void setZahlung(Zahlung zahlung) {
        this.zahlung = zahlung;
    }

    public abstract double preis();
}
