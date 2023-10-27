package toy.five.triprecord.domain.trip.validation.createtime;

import toy.five.triprecord.domain.trip.dto.request.TripCreateRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import static toy.five.triprecord.global.exception.ValidationCode.*;

//시간 검증
public class TripCreateTimeConstraintValidator implements ConstraintValidator<TripCreateTimeConstraint, TripCreateRequest> {

    @Override
    public void initialize(TripCreateTimeConstraint tripCreateTimeConstraint) {
    }

    @Override
    public boolean isValid(TripCreateRequest createRequest, ConstraintValidatorContext context) {
        if (createRequest.getStartTime() == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(TRIP_START_TIME_ERROR.getMessage())
                    .addConstraintViolation();
            return false;
        }
        if (createRequest.getEndTime() != null && createRequest.getStartTime() != null
                && createRequest.getStartTime().isAfter(createRequest.getEndTime())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(TRIP_TIME_ERROR.getMessage())
                    .addPropertyNode("startTime")
                    .addConstraintViolation();

            return false;
        }
        return true;
    }
}
