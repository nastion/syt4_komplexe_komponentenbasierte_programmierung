package pfuchs.syt4.westbahn.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;

@Entity
public class Sonderangebot {
	@Id
	@GeneratedValue()
	private Long Id;

    private int kontingent = 999;

	//@Future(message = "The Date may not be in the past")
	private Date startZeit;
	
	@AssertTrue(message = "The Date may not be in the past")
	private boolean dateIsNotInPast;
	
	private int dauer = 12;

	private float preisNachlass = 0.5f;

	@OneToMany
	private Set<Ticket> tickets;

	public Sonderangebot(Date startZeit) {
		this.startZeit = startZeit;
		this.dateIsNotInPast = !this.startZeit.before(new Date());
	}
	
	public Sonderangebot(int kontingent, Date startZeit, int dauer, float preisNachlass, Set<Ticket> tickets) {
		super();
		this.kontingent = kontingent;
		this.startZeit = startZeit;
		this.dauer = dauer;
		this.preisNachlass = preisNachlass;
		this.tickets = tickets;
		this.dateIsNotInPast = !this.startZeit.before(new Date());
	}

    public Sonderangebot() {
    }

    public Long getID() {
        return Id;
    }

    public void setID(Long ID) {
        this.Id = ID;
    }

    public int getKontingent() {
        return kontingent;
    }

    public void setKontingent(int kontingent) {
        this.kontingent = kontingent;
    }

    public Date getStartZeit() {
        return startZeit;
    }

    public void setStartZeit(Date startZeit) {
        this.startZeit = startZeit;
    }

    public boolean isDateIsNotInPast() {
        return dateIsNotInPast;
    }

    public void setDateIsNotInPast(boolean dateIsNotInPast) {
        this.dateIsNotInPast = dateIsNotInPast;
    }

    public int getDauer() {
        return dauer;
    }

    public void setDauer(int dauer) {
        this.dauer = dauer;
    }

    public float getPreisNachlass() {
        return preisNachlass;
    }

    public void setPreisNachlass(float preisNachlass) {
        this.preisNachlass = preisNachlass;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

}
