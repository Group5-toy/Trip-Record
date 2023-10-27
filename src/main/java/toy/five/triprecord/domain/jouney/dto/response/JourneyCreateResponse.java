package toy.five.triprecord.domain.jouney.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
//@NoArgsConstructor
@Builder
public class JourneyCreateResponse {

    private List<MoveJourneyCreateResponse> moves;
    private List<VisitJourneyCreateResponse> visits;
    private List<LodgmentJourneyCreateResponse> lodgments;

    public static JourneyCreateResponse of(List<MoveJourneyCreateResponse> moves,
                                           List<VisitJourneyCreateResponse> visits,
                                           List<LodgmentJourneyCreateResponse> lodgments)
    {
        return JourneyCreateResponse.builder()
                .moves(moves)       //moves 가 null이라면 에러발생, Nullable 처리 예정
                .visits(visits)     //마찬가지
                .lodgments(lodgments)
                .build();
    }

}
