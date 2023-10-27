package toy.five.triprecord.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //Trip
    TRIP_NO_EXIST(400,"해당 ID의 여행 정보가 없습니다."),


    //J
    JOURNEY_NO_EXIST(400,"해당 ID의 여행 정보가 없습니다.")

    ;

    private final int statusCode;
    private final String message;
}
