package toy.five.triprecord.domain.trip.validation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import toy.five.triprecord.domain.trip.dto.request.TripCreateRequest;
import toy.five.triprecord.domain.trip.dto.request.TripUpdateRequest;
import toy.five.triprecord.global.exception.BaseException;
import toy.five.triprecord.global.exception.ErrorCode;

@Component
@RequiredArgsConstructor
@Slf4j
public class TripValidation<T> {

    public void validateTimeTrip(T tripRequest) {

        if (tripRequest instanceof TripCreateRequest) {
            TripCreateRequest createRequest = (TripCreateRequest) tripRequest;
            if (createRequest.getStartTime() == null || createRequest.getEndTime() == null) {
                throw new BaseException(ErrorCode.TRIP_INVALID_TIME);
            }
            if (createRequest.getStartTime().isAfter(createRequest.getEndTime())) {
                throw new BaseException(ErrorCode.TRIP_INVALID_TIME);
            }
        }
        else if (tripRequest instanceof TripUpdateRequest) {
            TripUpdateRequest updateRequest = (TripUpdateRequest) tripRequest;

            if (updateRequest.getStartTime() == null || updateRequest.getEndTime() == null) {
                throw new BaseException(ErrorCode.TRIP_INVALID_TIME);
            }
            if (updateRequest.getStartTime().isAfter(updateRequest.getEndTime())) {
                throw new BaseException(ErrorCode.TRIP_INVALID_TIME);
            }
        }

    }

    public void validateNameDomesticTrip(T tripRequest) {
        if (tripRequest instanceof TripCreateRequest) {
            TripCreateRequest createRequest = (TripCreateRequest) tripRequest;
            if (createRequest.getName().isEmpty() || createRequest.getIsDomestic() == null) {
                throw new BaseException(ErrorCode.TRIP_INVALID_PARAMETER);
            }
        } else if (tripRequest instanceof TripUpdateRequest) {
            TripUpdateRequest updateRequest = (TripUpdateRequest) tripRequest;
            if (updateRequest.getName().isEmpty() || updateRequest.getIsDomestic() == null) {
                throw new BaseException(ErrorCode.TRIP_INVALID_PARAMETER);
            }
        }
    }
}
