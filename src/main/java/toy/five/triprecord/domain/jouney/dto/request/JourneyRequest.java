package toy.five.triprecord.domain.jouney.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JourneyRequest {

    private List<MoveJourneyRequest> moves;
    private List<VisitJourneyRequest> visits;
    private List<LodgmentJourneyRequest> lodgments;

}
