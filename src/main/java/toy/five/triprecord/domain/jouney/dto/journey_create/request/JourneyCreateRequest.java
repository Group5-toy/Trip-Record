package toy.five.triprecord.domain.jouney.dto.journey_create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JourneyCreateRequest {

    private List<MoveJourneyCreateRequest> moves;
    private List<VisitJourneyCreateRequest> visits;
    private List<LodgmentJourneyCreateRequest> lodgments;

}
