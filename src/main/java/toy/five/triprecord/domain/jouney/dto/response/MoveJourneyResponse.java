package toy.five.triprecord.domain.jouney.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.LastModifiedDate;
import toy.five.triprecord.domain.jouney.entity.JourneyType;
import toy.five.triprecord.domain.jouney.entity.MoveJourney;
import toy.five.triprecord.domain.trip.entity.Trip;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;

@Getter
@AllArgsConstructor
//@NoArgsConstructor
@Builder
public class MoveJourneyResponse {

    private Long tripId;
    private String name;
    private String vehicle;
    private String startPoint;
    private String endPoint;
    private JourneyType type;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;


    public static MoveJourneyResponse fromEntity(MoveJourney entity) {
        return MoveJourneyResponse.builder()
                .tripId(entity.getTrip().getId())
                .name(entity.getName())
                .vehicle(entity.getVehicle())
                .startPoint(entity.getStartPoint())
                .endPoint(entity.getEndPoint())
                .type(entity.getType())
                .createdTime(entity.getCreatedTime())
                .modifiedTime(entity.getModifiedTime())
                .build();
    }
}
