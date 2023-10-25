package toy.five.triprecord.domain.trip.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.five.triprecord.domain.trip.dto.request.TripCreateRequest;
import toy.five.triprecord.domain.trip.dto.request.TripUpdateRequest;
import toy.five.triprecord.domain.trip.dto.response.TripCreateResponse;
import toy.five.triprecord.domain.trip.dto.response.TripUpdateResponse;
import toy.five.triprecord.domain.trip.entity.Trip;
import toy.five.triprecord.domain.trip.repository.TripRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TripService {

    private final TripRepository tripRepository;

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

        Trip existingTrip = tripRepository.findById(tripId).orElseThrow(RuntimeException::new);

        TripUpdateRequest updateRequest = TripUpdateRequest.builder()
                .name(existingTrip.getName())
                .startTime(existingTrip.getStartTime())
                .endTime(existingTrip.getEndTime())
                .isDomestic(existingTrip.getIsDomestic())
                .build();

        updateRequest.updateFromTripCreateRequest(tripUpdateRequest);
        existingTrip.updateColumns(updateRequest);

        return TripUpdateResponse.fromEntity(existingTrip);

    }


}

