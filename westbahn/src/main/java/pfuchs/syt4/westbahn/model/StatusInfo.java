package pfuchs.syt4.westbahn.model;

import javax.persistence.Embeddable;

@Embeddable
public enum StatusInfo {
	DELAYED, CANCELLED, ONTIME
}
