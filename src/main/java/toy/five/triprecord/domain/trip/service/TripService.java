package toy.five.triprecord.domain.trip.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.five.triprecord.domain.trip.dto.TripEntryResponse;
import toy.five.triprecord.domain.trip.dto.request.TripCreateRequest;
import toy.five.triprecord.domain.trip.dto.request.TripPatchRequest;
import toy.five.triprecord.domain.trip.dto.request.TripUpdateRequest;
import toy.five.triprecord.domain.trip.dto.response.TripCreateResponse;
import toy.five.triprecord.domain.trip.dto.response.TripPatchResponse;
import toy.five.triprecord.domain.trip.dto.response.TripUpdateResponse;
import toy.five.triprecord.domain.trip.entity.Trip;
import toy.five.triprecord.domain.trip.repository.TripRepository;
import toy.five.triprecord.domain.trip.validation.patch.TripPatchTimeValidatorUtils;
import toy.five.triprecord.global.exception.BaseException;
import toy.five.triprecord.global.exception.ErrorCode;

import java.util.List;

import static toy.five.triprecord.global.exception.ErrorCode.TRIP_NO_EXIST;

@Service
@RequiredArgsConstructor
@Slf4j
public class TripService {

    private final TripRepository tripRepository;
    private final TripPatchTimeValidatorUtils tripPatchTimeValidatorUtils;


    @Transactional(readOnly = true)
    public TripEntryResponse getTripById(Long tripId) {

        return TripEntryResponse.fromEntity(findTripById(tripId));
    }

    @Transactional(readOnly = true)
    public List<TripEntryResponse> getAllTripsPaging(Pageable pageable) {
        return tripRepository.findAll(pageable)
                .map(TripEntryResponse::fromEntity).getContent();
    }

    private Trip findTripById(Long id) {
        return tripRepository.findById(id)
                .orElseThrow(() -> new BaseException(TRIP_NO_EXIST));
    }

    @Transactional
    public TripCreateResponse createTrip(TripCreateRequest tripCreateRequest) {

        Trip newTrip = Trip.builder()
                .name(tripCreateRequest.getName())
                .startTime(tripCreateRequest.getStartTime())
                .endTime(tripCreateRequest.getEndTime())
                .domestic(tripCreateRequest.getDomestic())
                .build();

        return TripCreateResponse.fromEntity(tripRepository.save(newTrip));
    }

    @Transactional
    public TripUpdateResponse updateTrip(Long tripId,TripUpdateRequest tripUpdateRequest) {

        Trip existingTrip = tripRepository.findById(tripId)
                .orElseThrow(() -> new BaseException(ErrorCode.TRIP_NO_EXIST));


        existingTrip.updateAllColumns(tripUpdateRequest);

        return TripUpdateResponse.fromEntity(existingTrip);

    }

    @Transactional
    public TripPatchResponse patchTrip(Long tripId, TripPatchRequest tripPatchRequest) {

        Trip existingTrip = tripRepository.findById(tripId)
                .orElseThrow(() -> new BaseException(ErrorCode.TRIP_NO_EXIST));

        TripPatchRequest updateRequest = TripPatchRequest.builder()
                .name(existingTrip.getName())
                .startTime(existingTrip.getStartTime())
                .endTime(existingTrip.getEndTime())
                .domestic(existingTrip.getDomestic())
                .build();

        if (tripPatchRequest.getStartTime() == null || tripPatchRequest.getEndTime() == null) {
            tripPatchTimeValidatorUtils.startTimeCheckFromPatchRequest(tripPatchRequest,updateRequest.getEndTime());
            tripPatchTimeValidatorUtils.endTimeCheckFromPatchRequest(tripPatchRequest,updateRequest.getStartTime());
        }


        updateRequest.PatchFromTripPatchRequest(tripPatchRequest);
        existingTrip.updateColumns(updateRequest);

        return TripPatchResponse.fromEntity(existingTrip);

    }
}

