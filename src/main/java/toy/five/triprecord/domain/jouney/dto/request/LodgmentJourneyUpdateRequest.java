package toy.five.triprecord.domain.jouney.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.five.triprecord.domain.jouney.entity.JourneyType;
import toy.five.triprecord.domain.jouney.entity.LodgmentJourney;
import toy.five.triprecord.domain.trip.entity.Trip;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LodgmentJourneyUpdateRequest {

    @NotNull
    private String name;
    @NotNull
    private String dormitoryName;
    @NotNull
    private LocalDateTime startTime;
    @NotNull
    private LocalDateTime endTime;

}
