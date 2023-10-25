package toy.five.triprecord.domain.jouney.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.five.triprecord.domain.jouney.entity.MoveJourney;
import toy.five.triprecord.domain.jouney.entity.VisitJourney;

public interface VisitJourneyRepository extends JpaRepository<VisitJourney, Long> {
}
