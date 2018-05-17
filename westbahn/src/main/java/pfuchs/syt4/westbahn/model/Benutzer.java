package pfuchs.syt4.westbahn.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

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


@Entity
public class Benutzer implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;

	private String vorName;

	private String nachName;

	@Column(unique = true)
	@Pattern(regexp="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$",
			 message = "This E-Mail must not be wrong!")
	private String eMail;

	private String passwort;

	private String smsNummer;

	private Long verbuchtePraemienMeilen;

	@OneToOne(cascade = CascadeType.ALL)
	private Ticket tickets;
	
	public Benutzer() {
		
	}
	
	public Benutzer(String vorName, String nachName, String eMail) {
		super();
		this.vorName = vorName;
		this.nachName = nachName;
		this.eMail = eMail;
	}



	public Benutzer(String vorName, String nachName, String eMail, String passwort, String smsNummer,
			Long verbuchtePraemienMeilen, Ticket tickets) {
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

}
