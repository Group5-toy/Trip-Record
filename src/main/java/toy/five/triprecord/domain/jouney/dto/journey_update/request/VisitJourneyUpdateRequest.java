package toy.five.triprecord.domain.jouney.dto.journey_update.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.five.triprecord.domain.jouney.entity.JourneyType;
import toy.five.triprecord.domain.jouney.entity.VisitJourney;
import toy.five.triprecord.domain.trip.entity.Trip;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VisitJourneyUpdateRequest {

    private Trip trip;
    private String name;
    private String location;
    private JourneyType type;

    public void updateCheck(VisitJourneyUpdateRequest request) {
        if(request.getName() != null) {
            this.name = request.getName();
        }
        if(request.getName() != null) {
            this.location = request.getLocation();
        }
    }
}
