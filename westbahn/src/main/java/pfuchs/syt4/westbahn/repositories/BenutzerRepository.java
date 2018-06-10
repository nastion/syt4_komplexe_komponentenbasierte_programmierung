package pfuchs.syt4.westbahn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pfuchs.syt4.westbahn.model.Benutzer;

public interface BenutzerRepository extends JpaRepository<Benutzer, Long> {

}
