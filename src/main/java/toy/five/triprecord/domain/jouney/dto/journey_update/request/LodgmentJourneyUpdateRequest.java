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
public class LodgmentJourneyUpdateRequest {

    private String name;
    private String dormitoryName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

//    public void updateCheck(LodgmentJourneyUpdateRequest request) {
//        if(request.getName() != null) {
//            this.name = request.getName();
//        }
//        if(request.getName() != null) {
//            this.dormitoryName = request.getDormitoryName();
//        }
//    }

}
