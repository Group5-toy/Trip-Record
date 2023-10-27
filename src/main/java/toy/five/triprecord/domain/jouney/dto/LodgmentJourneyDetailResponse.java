package toy.five.triprecord.domain.jouney.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class LodgmentJourneyDetailResponse {

    private Long tripId;
    private String name;
    private String dormitoryName;
    private JourneyType type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public static LodgmentJourneyDetailResponse fromEntity(LodgmentJourney entity) {
        return LodgmentJourneyDetailResponse.builder()
                .tripId(entity.getTrip().getId())
                .name(entity.getName())
                .dormitoryName(entity.getDormitoryName())
                .type(entity.getType())
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .build();
    }

}
