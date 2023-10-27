package toy.five.triprecord.domain.jouney.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
//@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class JourneysDetailResponse {

    private List<MoveJourneyDetailResponse> moves;
    private List<VisitJourneyDetailResponse> visits;
    private List<LodgmentJourneyDetailResponse> lodgments;

    public static JourneysDetailResponse of(
            List<MoveJourneyDetailResponse> moves,
            List<VisitJourneyDetailResponse> visits,
            List<LodgmentJourneyDetailResponse> lodgments
    ) {
        return JourneysDetailResponse.builder()
                .moves(moves)       //moves 가 null이라면 에러발생, Nullable 처리 예정
                .visits(visits)     //마찬가지
                .lodgments(lodgments)
                .build();
    }

}
