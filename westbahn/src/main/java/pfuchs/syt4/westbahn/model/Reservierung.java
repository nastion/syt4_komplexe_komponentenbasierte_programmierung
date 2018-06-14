package pfuchs.syt4.westbahn.model;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Reservierung {
    @Id
    @GeneratedValue
	private Long Id;

    @FutureOrPresent
	private Date datum;

	private int praemienMeilenBonus = 15;

	private int preis = 150;

	@Enumerated
	private StatusInfo status;

	@Embedded
    private Reservierungsart reservierungsart;

	@ManyToOne
	private Zug zug;

	@ManyToOne
    @NotNull
	private Strecke strecke;

	@OneToOne
    @NotNull
	private Benutzer benutzer;

	@Embedded
	private Zahlung zahlung;

	public Reservierung() {}
	public Reservierung(Date datum, StatusInfo status, Zug zug, Benutzer benutzer, Zahlung zahlung) {
		this(datum, 15, 150, status, zug, null, benutzer, zahlung);
	}
	public Reservierung(Date datum, StatusInfo status, Strecke strecke, Benutzer benutzer, Zahlung zahlung) {
		this(datum, 15, 150, status, null, strecke, benutzer, zahlung);
	}

	public Reservierung(Date datum, int praemienMeilenBonus, int preis, StatusInfo status, Zug zug, Strecke strecke,
			Benutzer benutzer, Zahlung zahlung) {
		super();
		this.datum = datum;
		this.praemienMeilenBonus = praemienMeilenBonus;
		this.preis = preis;
		this.status = status;
		this.zug = zug;
		this.strecke = strecke;
		this.benutzer = benutzer;
		this.zahlung = zahlung;
	}
	public String showReservierung() {
		return benutzer.getName() + " hat am " + datum.toString() + " einen Zug nach " + strecke.getEndbahnhof() + " reserviert.";
	}
	
	public Benutzer getBenutzer() {
		return this.benutzer;
	}

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getPraemienMeilenBonus() {
        return praemienMeilenBonus;
    }

    public void setPraemienMeilenBonus(int praemienMeilenBonus) {
        this.praemienMeilenBonus = praemienMeilenBonus;
    }

    public int getPreis() {
        return preis;
    }

    public void setPreis(int preis) {
        this.preis = preis;
    }

    public StatusInfo getStatus() {
        return status;
    }

    public void setStatus(StatusInfo status) {
        this.status = status;
    }

    public Zug getZug() {
        return zug;
    }

    public void setZug(Zug zug) {
        this.zug = zug;
    }

    public Strecke getStrecke() {
        return strecke;
    }

    public void setStrecke(Strecke strecke) {
        this.strecke = strecke;
    }

    public void setBenutzer(Benutzer benutzer) {
        this.benutzer = benutzer;
    }

    public Zahlung getZahlung() {
        return zahlung;
    }

    public void setZahlung(Zahlung zahlung) {
        this.zahlung = zahlung;
    }

    public Reservierungsart getReservierungsart() {
        return reservierungsart;
    }

    public void setReservierungsart(Reservierungsart reservierungsart) {
        this.reservierungsart = reservierungsart;
    }
}
