package toy.five.triprecord.domain.trip.dto.request;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TripUpdateRequest {
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Boolean isDomestic;

    public void updateFromTripCreateRequest(TripUpdateRequest tripUpdateRequest) {
        if (!tripUpdateRequest.getName().isEmpty()) {
            this.name = tripUpdateRequest.getName();
        }
        if (tripUpdateRequest.getStartTime() != null) {
            this.startTime = tripUpdateRequest.getStartTime();
        }
        if (tripUpdateRequest.getEndTime() != null) {
            this.endTime = tripUpdateRequest.getEndTime();
        }
        if (tripUpdateRequest.getIsDomestic() != null) {
            this.isDomestic = tripUpdateRequest.getIsDomestic();
        }
    }




}