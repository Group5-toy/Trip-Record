package toy.five.triprecord.domain.trip.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.five.triprecord.domain.trip.validation.patchtime.TripPatchTimeConstraint;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@TripPatchTimeConstraint
public class TripPatchRequest {


    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Boolean isDomestic;


    public void PatchFromTripPatchRequest(TripPatchRequest tripPatchRequest) {
        if (!tripPatchRequest.getName().isEmpty()) {
            this.name = tripPatchRequest.getName();
        }
        if (tripPatchRequest.getStartTime() != null) {
            this.startTime = tripPatchRequest.getStartTime();
        }
        if (tripPatchRequest.getEndTime() != null) {
            this.endTime = tripPatchRequest.getEndTime();
        }
        if (tripPatchRequest.getIsDomestic() != null) {
            this.isDomestic = tripPatchRequest.getIsDomestic();
        }
    }



}
