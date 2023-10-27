package toy.five.triprecord.domain.jouney.dto.journey_update.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MoveJourneyUpdateRequest {

    private String name;
    private String vehicle;
    private String startPoint;
    private String endPoint;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

//    public void updateCheck(MoveJourneyUpdateRequest request) {
//        if(request.getName() != null) {
//            this.name = request.getName();
//        }
//        if(request.getName() != null) {
//            this.vehicle = request.getVehicle();
//        }
//        if(request.getName() != null) {
//            this.startPoint = request.getStartPoint();
//        }
//        if(request.getName() != null) {
//            this.endPoint = request.getEndPoint();
//        }
//    }
}
