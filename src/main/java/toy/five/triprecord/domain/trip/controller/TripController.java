package toy.five.triprecord.domain.trip.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toy.five.triprecord.domain.trip.dto.TripEntryResponse;
import toy.five.triprecord.domain.trip.dto.request.TripCreateRequest;
import toy.five.triprecord.domain.trip.dto.request.TripUpdateRequest;
import toy.five.triprecord.domain.trip.dto.response.TripCreateResponse;
import toy.five.triprecord.domain.trip.dto.response.TripUpdateResponse;
import toy.five.triprecord.domain.trip.service.TripService;
import toy.five.triprecord.domain.trip.validation.TripValidation;
import toy.five.triprecord.global.exception.ApiResponse;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/trips")
public class TripController {

    private final TripService tripService;
    private final TripValidation tripValidation;

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
    public ResponseEntity<ApiResponse> createTrip(@RequestBody TripCreateRequest tripCreateRequest) {
        tripValidation.validateNameDomesticTrip(tripCreateRequest);
        tripValidation.validateTimeTrip(tripCreateRequest);

        TripCreateResponse savedTrip = tripService.createTrip(tripCreateRequest);

        return ResponseEntity.ok(ApiResponse.builder().status("Success").code(HttpStatus.OK.value()).data(savedTrip).build());

    }

    /**
     * 여행 수정 요청
     *
     * @param tripId {@link Long} 여행글 ID 요청 파라미터
     * @param tripUpdateRequest {@link TripCreateRequest} 여행 수정 내용 요청 파라미터
     * @return {@link ResponseEntity}
     **/


    @PutMapping("/{tripId}")
    public ResponseEntity<ApiResponse> updateTrip(@PathVariable Long tripId, @RequestBody TripUpdateRequest tripUpdateRequest) {
        tripValidation.validateNameDomesticTrip(tripUpdateRequest);
        tripValidation.validateTimeTrip(tripUpdateRequest);

        TripUpdateResponse savedTrip = tripService.updateTrip(tripId,tripUpdateRequest);

        return ResponseEntity.ok(ApiResponse.builder().status("Success").code(HttpStatus.OK.value()).data(savedTrip).build());

    }

    @PostConstruct
    public void init() {
        tripService.createTrip(
                TripCreateRequest.builder()
                        .name("여행1")
                        .startTime(LocalDateTime.now())
                        .endTime(LocalDateTime.now())
                        .isDomestic(true)
                        .build()
        );

        tripService.createTrip(
                TripCreateRequest.builder()
                        .name("여행2")
                        .startTime(LocalDateTime.now())
                        .endTime(LocalDateTime.now())
                        .isDomestic(false)
                        .build()
        );
    }
}