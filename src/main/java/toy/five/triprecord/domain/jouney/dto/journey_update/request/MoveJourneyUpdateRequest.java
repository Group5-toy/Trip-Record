package toy.five.triprecord.domain.jouney.dto.journey_update.request;

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
public class MoveJourneyUpdateRequest {

    private String name;
    private String vehicle;
    private String startPoint;
    private String endPoint;
    private JourneyType type;

    public void updateCheck(MoveJourneyUpdateRequest request) {
        if(request.getName() != null) {
            this.name = request.getName();
        }
        if(request.getName() != null) {
            this.vehicle = request.getVehicle();
        }
        if(request.getName() != null) {
            this.startPoint = request.getStartPoint();
        }
        if(request.getName() != null) {
            this.endPoint = request.getEndPoint();
        }
    }
}
