package pfuchs.syt4.westbahn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pfuchs.syt4.westbahn.model.Benutzer;

@Repository
public interface BenutzerRepository extends JpaRepository<Benutzer, Long> {
    Benutzer findByEMail(String email);
}
