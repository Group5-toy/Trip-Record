package toy.five.triprecord.domain.jouney.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import toy.five.triprecord.domain.jouney.entity.JourneyType;
import toy.five.triprecord.domain.jouney.entity.VisitJourney;
import toy.five.triprecord.domain.trip.entity.Trip;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
//@NoArgsConstructor
@Builder
public class VisitJourneyResponse {

    private Trip trip;
    private String name;
    private String location;
    private JourneyType type;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;

    public static VisitJourneyResponse fromEntity(VisitJourney entity) {
        return VisitJourneyResponse.builder()
                .trip(entity.getTrip())
                .name(entity.getName())
                .location(entity.getLocation())
                .type(entity.getType())
                .createdTime(entity.getCreatedTime())
                .modifiedTime(entity.getModifiedTime())
                .build();
    }

}
