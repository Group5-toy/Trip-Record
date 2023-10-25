package toy.five.triprecord.domain.jouney.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.five.triprecord.domain.jouney.entity.JourneyType;
import toy.five.triprecord.domain.jouney.entity.MoveJourney;
import toy.five.triprecord.domain.trip.entity.Trip;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MoveJourneyRequest {

    private String name;
    private String vehicle;
    private String startPoint;
    private String endPoint;
    private JourneyType type;


}
