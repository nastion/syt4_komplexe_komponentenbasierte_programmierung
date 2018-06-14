package pfuchs.syt4.westbahn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pfuchs.syt4.westbahn.model.Benutzer;
import pfuchs.syt4.westbahn.model.Reservierung;

import java.util.List;

@Repository
public interface ReservierungRepository extends JpaRepository<Reservierung, Long> {
    List<Reservierung> findAllByBenutzer(Benutzer benutzer);
}
