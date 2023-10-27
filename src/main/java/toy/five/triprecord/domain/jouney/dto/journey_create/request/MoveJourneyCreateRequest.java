package toy.five.triprecord.domain.jouney.dto.journey_create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.five.triprecord.domain.jouney.entity.JourneyType;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MoveJourneyCreateRequest {

    private String name;
    private String vehicle;
    private String startPoint;
    private String endPoint;
    private JourneyType type;


}
