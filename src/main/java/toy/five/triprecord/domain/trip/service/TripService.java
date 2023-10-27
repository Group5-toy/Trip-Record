package toy.five.triprecord.domain.trip.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.five.triprecord.domain.trip.dto.TripEntryResponse;
import toy.five.triprecord.domain.trip.dto.request.TripCreateRequest;
import toy.five.triprecord.domain.trip.dto.request.TripUpdateRequest;
import toy.five.triprecord.domain.trip.dto.response.TripCreateResponse;
import toy.five.triprecord.domain.trip.dto.response.TripUpdateResponse;
import toy.five.triprecord.domain.trip.entity.Trip;
import toy.five.triprecord.domain.trip.repository.TripRepository;
import toy.five.triprecord.global.exception.BaseException;

import java.util.List;

import static toy.five.triprecord.global.exception.ErrorCode.TRIP_NO_EXIST;

@Service
@RequiredArgsConstructor

@Slf4j
public class TripService {

    private final TripRepository tripRepository;

    @Transactional(readOnly = true)
    public TripEntryResponse getTripById(Long tripId) {

        return TripEntryResponse.fromEntity(findTripById(tripId));
    }

    @Transactional(readOnly = true)
    public List<TripEntryResponse> getAllTripsPaging(Pageable pageable) {
        return tripRepository.findAll(pageable)    //Nullable 발생 시 처리 예정
                .map(TripEntryResponse::fromEntity).getContent();
    }

    private Trip findTripById(Long id) {
        return tripRepository.findById(id)
                .orElseThrow(() -> new BaseException(TRIP_NO_EXIST));    //Exception 별도 처리 예정
    }

    @Transactional
    public TripCreateResponse createTrip(TripCreateRequest tripCreateRequest) {

        Trip newTrip = Trip.builder()
                .name(tripCreateRequest.getName())
                .startTime(tripCreateRequest.getStartTime())
                .endTime(tripCreateRequest.getEndTime())
                .isDomestic(tripCreateRequest.getIsDomestic())
                .build();

        return TripCreateResponse.fromEntity(tripRepository.save(newTrip));
    }

    @Transactional
    public TripUpdateResponse updateTrip(Long tripId, TripUpdateRequest tripUpdateRequest) {

        Trip findTrip = findTripById(tripId);
        findTrip.updateColumns(tripUpdateRequest);

        return TripUpdateResponse.fromEntity(findTrip);

    }


}

