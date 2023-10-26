package toy.five.triprecord.domain.jouney.dto.journey_create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.five.triprecord.domain.jouney.entity.JourneyType;
import toy.five.triprecord.domain.jouney.entity.LodgmentJourney;
import toy.five.triprecord.domain.trip.entity.Trip;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LodgmentJourneyCreateRequest {

    private String name;
    private String dormitoryName;
    private JourneyType type;


    public LodgmentJourney toEntity(Trip trip) {
        return LodgmentJourney.builder()
                .trip(trip)
                .name(this.name)
                .dormitoryName(this.dormitoryName)
                .type(this.type)
                .build();
    }

}
