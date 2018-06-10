package pfuchs.syt4.westbahn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pfuchs.syt4.westbahn.model.Benutzer;
import pfuchs.syt4.westbahn.model.Zug;

@Repository
public interface ZugRepository extends JpaRepository<Zug, Long> {

}
