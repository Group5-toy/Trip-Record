package toy.five.triprecord.domain.trip.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import toy.five.triprecord.domain.trip.dto.TripEntryResponse;
import toy.five.triprecord.domain.trip.service.TripService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @GetMapping("/trips/{tripId}")
    public TripEntryResponse getTrip(@PathVariable final Long tripId) {
        log.info("GET /trips/{tripId} HTTP/1.1");

        return tripService.getTripById(tripId);
    }

    @GetMapping("/trips/all")
    public List<TripEntryResponse> getAllTrips() {
        log.info("GET /trips/all HTTP/1.1");

        return tripService.getAllTrips();
    }

}
