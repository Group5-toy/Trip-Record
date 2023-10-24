package toy.five.triprecord.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //Trip
    BAD_REQUEST("잘못된 요청")
    ;

    //J

    private final String message;
}
