package toy.five.triprecord.domain.jouney.dto.journey_update.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import toy.five.triprecord.domain.jouney.entity.JourneyType;
import toy.five.triprecord.domain.jouney.entity.VisitJourney;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
//@NoArgsConstructor
@Builder
public class VisitJourneyUpdateResponse {

    private Long tripId;
    private String name;
    private String location;
    private JourneyType type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public static VisitJourneyUpdateResponse fromEntity(VisitJourney entity) {
        return VisitJourneyUpdateResponse.builder()
                .tripId(entity.getTrip().getId())
                .name(entity.getName())
                .location(entity.getLocation())
                .type(entity.getType())
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .build();
    }

}
