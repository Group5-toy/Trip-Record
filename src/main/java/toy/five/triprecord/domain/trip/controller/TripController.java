package toy.five.triprecord.domain.trip.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toy.five.triprecord.domain.trip.dto.request.TripCreateRequest;
import toy.five.triprecord.domain.trip.dto.request.TripUpdateRequest;
import toy.five.triprecord.domain.trip.dto.response.TripCreateResponse;
import toy.five.triprecord.domain.trip.dto.response.TripUpdateResponse;
import toy.five.triprecord.domain.trip.service.TripService;
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
@RequestMapping("/trips")
public class TripController {

    private final TripService tripService;

    @GetMapping("/{tripId}")
    public TripEntryResponse getTrip(@PathVariable final Long tripId) {
        log.info("GET /trips/{tripId} HTTP/1.1");

        return tripService.getTripById(tripId);
    }

    @GetMapping("/all")
    public List<TripEntryResponse> getAllTrips() {
        log.info("GET /trips/all HTTP/1.1");

        return tripService.getAllTrips();
    }

    /**
     * 여행 등록 요청
     *
     * @param tripCreateRequest {@link TripCreateRequest} 여행 등록 요청 파라미터
     * @return {@link ResponseEntity}
     **/

    @PostMapping
    public ResponseEntity<TripCreateResponse> createTrip(@RequestBody TripCreateRequest tripCreateRequest) {
        TripCreateResponse savedTrip = tripService.createTrip(tripCreateRequest);
        return ResponseEntity.ok(savedTrip);
        //exceptionHandler로 처리 예정
    }

    /**
     * 여행 수정 요청
     *
     * @param tripId {@link Long} 여행글 ID 요청 파라미터
     * @param tripCreateRequest {@link TripCreateRequest} 여행 수정 내용 요청 파라미터
     * @return {@link ResponseEntity}
     **/


    @PutMapping("/{tripId}")
    public ResponseEntity<TripUpdateResponse> updateTrip(@PathVariable Long tripId, @RequestBody TripUpdateRequest tripCreateRequest) {
        TripUpdateResponse savedTrip = tripService.updateTrip(tripId,tripCreateRequest);
        return ResponseEntity.ok(savedTrip);
        //exceptionHandler로 처리 예정
    }
}