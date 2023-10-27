package toy.five.triprecord.domain.trip.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.five.triprecord.domain.jouney.dto.response.LodgmentJourneyCreateResponse;
import toy.five.triprecord.domain.jouney.dto.response.MoveJourneyCreateResponse;
import toy.five.triprecord.domain.jouney.dto.response.VisitJourneyCreateResponse;
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
    private List<MoveJourneyCreateResponse> moveJourneys;
    private List<LodgmentJourneyCreateResponse> lodgmentJourneys;
    private List<VisitJourneyCreateResponse> visitJourneys;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @Enumerated(EnumType.STRING)
    private Boolean isDomestic;

    public static TripEntryResponse fromEntity(Trip trip) {
        return TripEntryResponse.builder()
                .id(trip.getId())
                .name(trip.getName())
                .moveJourneys(trip.getMoveJourneys().stream()
                        .map(MoveJourneyCreateResponse::fromEntity).toList())
                .lodgmentJourneys(trip.getLodgmentJourneys().stream()
                        .map(LodgmentJourneyCreateResponse::fromEntity).toList())
                .visitJourneys(trip.getVisitJourneys().stream()
                        .map(VisitJourneyCreateResponse::fromEntity).toList())
                .startTime(trip.getStartTime())
                .endTime(trip.getEndTime())
                .isDomestic(trip.getIsDomestic())
                .build();
    }
}
