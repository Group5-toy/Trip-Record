package toy.five.triprecord.domain.jouney.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
//@NoArgsConstructor
@Builder
public class JourneyResponse {

    private List<MoveJourneyResponse> moves;
    private List<VisitJourneyResponse> visits;
    private List<LodgmentJourneyResponse> lodgments;

    public static JourneyResponse of(List<MoveJourneyResponse> moves, List<VisitJourneyResponse> visits, List<LodgmentJourneyResponse> lodgments) {
        return JourneyResponse.builder()
                .moves(moves)
                .visits(visits)
                .lodgments(lodgments)
                .build();
    }

}
