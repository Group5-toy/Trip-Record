package toy.five.triprecord.domain.trip.validation.updatetime;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import toy.five.triprecord.domain.trip.dto.request.TripUpdateRequest;

import static toy.five.triprecord.global.exception.ValidationCode.TRIP_START_TIME_ERROR;
import static toy.five.triprecord.global.exception.ValidationCode.TRIP_TIME_ERROR;

public class TripUpdateTimeConstraintValidator implements ConstraintValidator<TripUpdateTimeConstraint, TripUpdateRequest>{

    @Override
    public void initialize(TripUpdateTimeConstraint tripUpdateTimeConstraint) {
    }

    @Override
    public boolean isValid(TripUpdateRequest updateRequest, ConstraintValidatorContext context) {
        if (updateRequest.getStartTime() == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(TRIP_START_TIME_ERROR.getMessage())
                    .addPropertyNode("startTime").addConstraintViolation();
            return false;
        }
        if (updateRequest.getEndTime() != null && updateRequest.getStartTime() != null
                && updateRequest.getStartTime().isAfter(updateRequest.getEndTime())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(TRIP_TIME_ERROR.getMessage())
                    .addPropertyNode("startTime").addConstraintViolation();
            return false;
        }

        return true;
    }

}
