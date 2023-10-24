package toy.five.triprecord.domain.trip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.five.triprecord.domain.trip.dto.TripEntryResponse;
import toy.five.triprecord.domain.trip.entity.Trip;
import toy.five.triprecord.domain.trip.repository.TripRepository;
import toy.five.triprecord.global.exception.BaseException;
import toy.five.triprecord.global.exception.ErrorCode;

import java.util.List;
import java.util.stream.Collectors;

import static toy.five.triprecord.global.exception.ErrorCode.TRIP_NO_EXIST;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;

    @Transactional(readOnly = true)
    public TripEntryResponse getTripById(Long tripId) {
        return TripEntryResponse.fromEntity(findTripById(tripId));
    }

    @Transactional(readOnly = true)
    public List<TripEntryResponse> getAllTrips() {
        return tripRepository.findAll().stream()    //Nullable 발생 시 처리 예정
                .map(TripEntryResponse::fromEntity).toList();   //pageable 고려 논의 필요.
    }

    private Trip findTripById(Long id) {
        return tripRepository.findById(id)
                .orElseThrow(() -> new BaseException(TRIP_NO_EXIST));    //Exception 별도 처리 예정
    }
}
