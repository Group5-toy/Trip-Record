package toy.five.triprecord.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponse<String>> handleBaseException(BaseException e) {
        return new ResponseEntity<>(
                ApiResponse.fail(e.getStatusCode(), e.getMessage()),
                HttpStatus.valueOf(e.getStatusCode())
        );
    }

}