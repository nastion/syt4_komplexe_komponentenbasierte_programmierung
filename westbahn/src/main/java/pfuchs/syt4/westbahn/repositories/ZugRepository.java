package pfuchs.syt4.westbahn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pfuchs.syt4.westbahn.model.Benutzer;
import pfuchs.syt4.westbahn.model.Zug;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface ZugRepository extends JpaRepository<Zug, Long> {
    @Query(value = "SELECT * FROM ZUG WHERE START_ZEIT like %?1%", nativeQuery = true)
    List<Zug> findByMatchMonthAndMatchDay(@Param("eventDate") String eventDate);

    @Query(value = "FROM Zug z WHERE z.start.name like 'Wien%'")
    Set<Zug> findAllFromWien();

    @Query(value = "FROM Zug z WHERE z.start.name like 'Salzburg'")
    Set<Zug> findAllFromSalzburg();

    List<Zug> findByStartZeit(Date startZeit);
}
