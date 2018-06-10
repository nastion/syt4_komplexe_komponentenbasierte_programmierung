package pfuchs.syt4.westbahn.model;

import javax.persistence.*;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotNull;

@Entity
public class Strecke {
	@Id
	@GeneratedValue
	private Long Id;
	
	@NotNull
	@OneToOne
	private Bahnhof start;

	@NotNull
	@OneToOne
	private Bahnhof ende;
	
	@AssertFalse(message="The starting station must not be the ending station")
	private boolean endeIsStart;

	public Strecke() {}
	public Strecke(Bahnhof start, Bahnhof ende) {
		this.start = start;
		this.ende = ende;
		this.endeIsStart = this.ende.getName().equals(this.start.getName());
	}
	public String getEndbahnhof() {
		return ende.getName();
	}

    public Long getID() {
        return Id;
    }

    public void setID(Long ID) {
        this.Id = ID;
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
}
