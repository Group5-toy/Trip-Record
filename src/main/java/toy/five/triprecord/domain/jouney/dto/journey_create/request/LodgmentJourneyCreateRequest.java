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
public class LodgmentJourneyCreateRequest {

    private String name;
    private String dormitoryName;
    private JourneyType type;


}
