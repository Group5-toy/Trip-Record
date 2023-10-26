package toy.five.triprecord.domain.jouney.dto.journey_update.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JourneyUpdateRequest {

    private List<MoveJourneyUpdateRequest> moves;
    private List<VisitJourneyUpdateRequest> visits;
    private List<LodgmentJourneyUpdateRequest> lodgments;

}
