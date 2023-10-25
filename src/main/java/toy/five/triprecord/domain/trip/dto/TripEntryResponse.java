package toy.five.triprecord.domain.trip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.five.triprecord.domain.jouney.dto.response.LodgmentJourneyResponse;
import toy.five.triprecord.domain.jouney.dto.response.MoveJourneyResponse;
import toy.five.triprecord.domain.jouney.dto.response.VisitJourneyResponse;
import toy.five.triprecord.domain.trip.entity.Trip;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TripEntryResponse {

    private Long id;
    private String name;
    private List<MoveJourneyResponse> moveJourneys;
    private List<LodgmentJourneyResponse> lodgmentJourneys;
    private List<VisitJourneyResponse> visitJourneys;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Boolean isDomestic;

    public static TripEntryResponse fromEntity(Trip trip) {
        return TripEntryResponse.builder()
                .id(trip.getId())
                .name(trip.getName())
                .moveJourneys(trip.getMoveJourneys().stream()
                        .map(MoveJourneyResponse::fromEntity).toList())
                .lodgmentJourneys(trip.getLodgmentJourneys().stream()
                        .map(LodgmentJourneyResponse::fromEntity).toList())
                .visitJourneys(trip.getVisitJourneys().stream()
                        .map(VisitJourneyResponse::fromEntity).toList())
                .startTime(trip.getStartTime())
                .endTime(trip.getEndTime())
                .isDomestic(trip.getIsDomestic())
                .build();
    }
}
