package toy.five.triprecord.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //Trip
    TRIP_NO_EXIST("해당 ID의 여행 정보가 없습니다."),
    ;

    //J

    private final String message;
}
