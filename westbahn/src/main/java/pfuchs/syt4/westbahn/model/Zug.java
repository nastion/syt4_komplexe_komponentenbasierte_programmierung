package pfuchs.syt4.westbahn.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.AssertFalse;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Zug {	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy="increment")
	private Long ID;

	private Date startZeit;

	private int sitzPlaetze = 500;

	private int fahrradStellplaetze = 50;

	private int rollStuhlPlaetze = 10;

	@AssertFalse(message="The starting station must not be the ending station")
	private boolean endeIsStart;

	@OneToOne
	private Bahnhof start;

	@OneToOne
	private Bahnhof ende;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Date getStartZeit() {
        return startZeit;
    }

    public void setStartZeit(Date startZeit) {
        this.startZeit = startZeit;
    }

    public int getSitzPlaetze() {
        return sitzPlaetze;
    }

    public void setSitzPlaetze(int sitzPlaetze) {
        this.sitzPlaetze = sitzPlaetze;
    }

    public int getFahrradStellplaetze() {
        return fahrradStellplaetze;
    }

    public void setFahrradStellplaetze(int fahrradStellplaetze) {
        this.fahrradStellplaetze = fahrradStellplaetze;
    }

    public int getRollStuhlPlaetze() {
        return rollStuhlPlaetze;
    }

    public void setRollStuhlPlaetze(int rollStuhlPlaetze) {
        this.rollStuhlPlaetze = rollStuhlPlaetze;
    }

    public boolean isEndeIsStart() {
        return endeIsStart;
    }

    public void setEndeIsStart(boolean endeIsStart) {
        this.endeIsStart = endeIsStart;
    }

    public Bahnhof getStart() {
        return start;
    }

    public void setStart(Bahnhof start) {
        this.start = start;
    }

    public Bahnhof getEnde() {
        return ende;
    }

    public void setEnde(Bahnhof ende) {
        this.ende = ende;
    }

    public Zug() {}
	public Zug(Bahnhof start, Bahnhof ende) {
		this(start, ende, new Date(), 100, 10, 10);
	}
	
	public Zug(Bahnhof start, Bahnhof ende, Date startZeit, int sitzPlaetze, int fahrradStellplaetze, int rollStuhlPlaetze) {
		this.start = start;
		this.ende = ende;
		this.startZeit = startZeit;
		this.sitzPlaetze = sitzPlaetze;
		this.fahrradStellplaetze = fahrradStellplaetze;
		this.rollStuhlPlaetze = rollStuhlPlaetze;
		this.endeIsStart = this.start.getName().equals(this.ende.getName());
	}

}
