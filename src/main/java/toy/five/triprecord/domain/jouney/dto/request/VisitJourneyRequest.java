package toy.five.triprecord.domain.jouney.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.five.triprecord.domain.jouney.entity.JourneyType;
import toy.five.triprecord.domain.jouney.entity.LodgmentJourney;
import toy.five.triprecord.domain.jouney.entity.MoveJourney;
import toy.five.triprecord.domain.jouney.entity.VisitJourney;
import toy.five.triprecord.domain.trip.entity.Trip;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VisitJourneyRequest {

    private String name;
    private String location;
    private JourneyType type;

}
