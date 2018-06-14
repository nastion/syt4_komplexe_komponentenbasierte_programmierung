package pfuchs.syt4.westbahn.model;

import javax.persistence.*;

@Embeddable
public class Maestro implements Zahlung {


	/**
	 * @see Zahlung#zahlungDurchfuehren()
	 * 
	 *  
	 */
	public void zahlungDurchfuehren() {
        System.out.println("Zahlung über Maestro");
	}

}
