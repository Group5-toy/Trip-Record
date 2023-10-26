package toy.five.triprecord.domain.jouney.dto.journey_create.request;

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
public class MoveJourneyCreateRequest {

    private String name;
    private String vehicle;
    private String startPoint;
    private String endPoint;
    private JourneyType type;

    //toEntity : TripCreateDto -> Trip
    //toEntity : MoverJourneyCreateDto -> MoveJourney
    //toEntity : Trip? -> MoveJourney
    public MoveJourney toEntity(Trip trip) {
        return MoveJourney.builder()
                .trip(trip)
                .name(this.name)
                .vehicle(this.vehicle)
                .startPoint(this.startPoint)
                .endPoint(this.endPoint)
                .type(this.type)
                .build();
    }


}
