package toy.five.triprecord.domain.jouney.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

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
                .moves(moves)       //moves 가 null이라면 에러발생, Nullable 처리 예정
                .visits(visits)     //마찬가지
                .lodgments(lodgments)
                .build();
    }

}
