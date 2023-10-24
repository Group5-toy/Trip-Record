package toy.five.triprecord.domain.trip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.five.triprecord.domain.trip.entity.Trip;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TripEntryResponse {

    private Long id;
    private String name;
//    private List<Journey> moveJourneys;
//    private List<Journey> moveJourneys;
//    private List<Journey> moveJourneys;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Boolean isDomestic;

    public static TripEntryResponse fromEntity(Trip trip) {
        return TripEntryResponse.builder()
                .id(trip.getId())
                .name(trip.getName())
                .startTime(trip.getStartTime())
                .endTime(trip.getEndTime())
                .isDomestic(trip.getIsDomestic())
                .build();
    }

}
