package pfuchs.syt4.westbahn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pfuchs.syt4.westbahn.model.Bahnhof;

@Repository
public interface BahnhofRepository extends JpaRepository<Bahnhof, Long> {

}
