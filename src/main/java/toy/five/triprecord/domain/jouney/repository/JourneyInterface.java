package toy.five.triprecord.domain.jouney.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.five.triprecord.domain.jouney.entity.Journey;

public interface JourneyInterface extends JpaRepository<Journey, Long> {
}
