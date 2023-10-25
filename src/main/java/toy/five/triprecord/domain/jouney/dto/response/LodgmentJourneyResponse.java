package toy.five.triprecord.domain.jouney.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import toy.five.triprecord.domain.jouney.entity.JourneyType;
import toy.five.triprecord.domain.jouney.entity.LodgmentJourney;
import toy.five.triprecord.domain.trip.entity.Trip;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
//@NoArgsConstructor
@Builder
public class LodgmentJourneyResponse {

    private Long tripId;
    private String name;
    private String dormitoryName;
    private JourneyType type;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;

    public static LodgmentJourneyResponse fromEntity(LodgmentJourney entity) {
        return LodgmentJourneyResponse.builder()
                .tripId(entity.getTrip().getId())
                .name(entity.getName())
                .dormitoryName(entity.getDormitoryName())
                .type(entity.getType())
                .createdTime(entity.getCreatedTime())
                .modifiedTime(entity.getModifiedTime())
                .build();
    }

}
