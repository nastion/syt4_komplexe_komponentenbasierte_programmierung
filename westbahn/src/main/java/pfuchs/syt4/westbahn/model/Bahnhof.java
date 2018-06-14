package pfuchs.syt4.westbahn.model;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Bahnhof {
	@Id
    @GeneratedValue
	private Long Id;
	
	@Size(min=2, max=150)
	@Pattern(regexp="^[\\p{L} .'-]+$", message="Ungültiger Name für den Bahnhof")
	private String name;
	
	private int absPreisEntfernung;
	
	private int absKmEntfernung;
	
	private int absZeitEntfernung;
	
	private boolean kopfBahnhof;
	
	public void setKopfBahnhof(boolean kopfBahnhof) {
		this.kopfBahnhof = kopfBahnhof;
	}
	
	public boolean getKopfBahnhof() {
		return this.kopfBahnhof;
	}
	
	public Bahnhof() {}
	
	public Bahnhof(String name) {
		this(name, 100, 100, 100, true);
	}
	
	public Bahnhof(String name, int absPreisEntfernung, int absKmEntfernung, int absZeitEntfernung, boolean kopfBahnhhof) {
		this.name = name;
		this.absPreisEntfernung = absPreisEntfernung;
		this.absKmEntfernung = absKmEntfernung;
		this.absZeitEntfernung = absZeitEntfernung;
		this.kopfBahnhof = kopfBahnhhof;
	}

	public String getName() {
		return name;
	}

    public Long getID() {
        return Id;
    }

    public void setID(Long ID) {
        this.Id = ID;
    }

    public int getAbsPreisEntfernung() {
        return absPreisEntfernung;
    }

    public void setAbsPreisEntfernung(int absPreisEntfernung) {
        this.absPreisEntfernung = absPreisEntfernung;
    }

    public int getAbsKmEntfernung() {
        return absKmEntfernung;
    }

    public void setAbsKmEntfernung(int absKmEntfernung) {
        this.absKmEntfernung = absKmEntfernung;
    }

    public int getAbsZeitEntfernung() {
        return absZeitEntfernung;
    }

    public void setAbsZeitEntfernung(int absZeitEntfernung) {
        this.absZeitEntfernung = absZeitEntfernung;
    }

    public boolean isKopfBahnhof() {
        return kopfBahnhof;
    }

    public void setName(String name) { this.name = name; }
	
}
