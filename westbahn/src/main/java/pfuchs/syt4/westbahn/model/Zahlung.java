package pfuchs.syt4.westbahn.model;

import javax.persistence.*;

@Embeddable
public interface Zahlung {
	
	public void zahlungDurchfuehren();

}

