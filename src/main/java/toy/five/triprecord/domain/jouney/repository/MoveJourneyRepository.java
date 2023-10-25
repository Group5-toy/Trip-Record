package toy.five.triprecord.domain.jouney.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.five.triprecord.domain.jouney.entity.MoveJourney;

public interface MoveJourneyRepository extends JpaRepository<MoveJourney, Long> {
}
