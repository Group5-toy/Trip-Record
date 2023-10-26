package toy.five.triprecord.domain.jouney.dto.journey_create.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import toy.five.triprecord.domain.jouney.entity.JourneyType;
import toy.five.triprecord.domain.jouney.entity.LodgmentJourney;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
//@NoArgsConstructor
@Builder
public class LodgmentJourneyCreateResponse {

    private Long tripId;
    private String name;
    private String dormitoryName;
    private JourneyType type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public static LodgmentJourneyCreateResponse fromEntity(LodgmentJourney entity) {
        return LodgmentJourneyCreateResponse.builder()
                .tripId(entity.getTrip().getId())
                .name(entity.getName())
                .dormitoryName(entity.getDormitoryName())
                .type(entity.getType())
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .build();
    }

}
