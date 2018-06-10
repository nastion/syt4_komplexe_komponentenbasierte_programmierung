package pfuchs.syt4.westbahn.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
/*
@NamedQueries({
	@NamedQuery(
		name = "getAllReservationsForEMail",
		query = "from Reservierung r inner join fetch r.benutzer as b "
				+ "WHERE b.eMail = :eMail"
	),
	@NamedQuery(
		name = "getAllUsersWithMonthTicket",
		query = "FROM Benutzer b inner join fetch Ticket t WHERE t.DTYPE = 'Zeitkarte'"
	),
	@NamedQuery(
		name = "getAllTicketsWithoutReservation",
		query = "FROM Reservierung r LEFT JOIN FETCH Benutzer b ON b.ID = r.ID "
				+ "RIGHT OUTER JOIN Ticket t ON t.ID = b.id "
				+ "LEFT OUTER JOIN Strecke s ON s.ID = t.id "
				+ "WHERE s.ende = :ende AND s.start = :start HAVING r.ID IS NULL"
	)
})
*/

@Entity
public class Benutzer implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@NotNull
	private String vorName;

	@NotNull
	private String nachName;

	@NotNull
    @Column(unique = true, name = "EMAIL")
	@Pattern(regexp="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$",
			 message = "This E-Mail must not be wrong!")
	private String eMail;

	@NotNull
	private String passwort;

	private String smsNummer;

	private Long verbuchtePraemienMeilen;

	@OneToMany
	private Set<Ticket> tickets;
	
	public Benutzer() {
		
	}
	
	public Benutzer(String vorName, String nachName, String eMail) {
		super();
		this.vorName = vorName;
		this.nachName = nachName;
		this.eMail = eMail;
	}



	public Benutzer(String vorName, String nachName, String eMail, String passwort, String smsNummer,
			Long verbuchtePraemienMeilen, Set<Ticket> tickets) {
		super();
		this.vorName = vorName;
		this.nachName = nachName;
		this.eMail = eMail;
		this.passwort = passwort;
		this.smsNummer = smsNummer;
		this.verbuchtePraemienMeilen = verbuchtePraemienMeilen;
		this.tickets = tickets;
	}


	public String getUser() {
		if (this.tickets.getClass().equals(pfuchs.syt4.westbahn.model.Zeitkarte.class))
			return this.vorName + " " + this.nachName + " (" + this.eMail + ") hat eine "
					+ ((Zeitkarte) this.tickets).getTyp();
		else return this.getName() + " (" + this.eMail + ") hat ein Einzelticket";
	}

	public String getName() {
		return vorName + " " + nachName;
	}

	public String getEMail() {
		return this.eMail;
	}

    public Long getID() {
        return Id;
    }

    public void setID(Long ID) {
        this.Id = ID;
    }

    public String getVorName() {
        return vorName;
    }

    public void setVorName(String vorName) {
        this.vorName = vorName;
    }

    public String getNachName() {
        return nachName;
    }

    public void setNachName(String nachName) {
        this.nachName = nachName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getSmsNummer() {
        return smsNummer;
    }

    public void setSmsNummer(String smsNummer) {
        this.smsNummer = smsNummer;
    }

    public Long getVerbuchtePraemienMeilen() {
        return verbuchtePraemienMeilen;
    }

    public void setVerbuchtePraemienMeilen(Long verbuchtePraemienMeilen) {
        this.verbuchtePraemienMeilen = verbuchtePraemienMeilen;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }
}
