package pfuchs.syt4.westbahn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pfuchs.syt4.westbahn.model.Bahnhof;
import pfuchs.syt4.westbahn.model.Benutzer;
import pfuchs.syt4.westbahn.model.Strecke;

@Repository
public interface StreckeRepository extends JpaRepository<Strecke, Long> {
    Strecke findByStartAndEnde(Bahnhof start, Bahnhof ende);
}
