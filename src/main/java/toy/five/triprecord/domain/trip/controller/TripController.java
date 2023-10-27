package toy.five.triprecord.domain.trip.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toy.five.triprecord.domain.trip.dto.TripEntryResponse;
import toy.five.triprecord.domain.trip.dto.request.TripCreateRequest;
import toy.five.triprecord.domain.trip.dto.request.TripPatchRequest;
import toy.five.triprecord.domain.trip.dto.request.TripUpdateRequest;
import toy.five.triprecord.domain.trip.dto.response.TripCreateResponse;
import toy.five.triprecord.domain.trip.dto.response.TripPatchResponse;
import toy.five.triprecord.domain.trip.dto.response.TripUpdateResponse;
import toy.five.triprecord.domain.trip.service.TripService;
import toy.five.triprecord.global.exception.ApiResponse;
import org.springframework.http.HttpStatus;
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
    public List<TripEntryResponse> getAllTrips(
            @PageableDefault(size=5, sort = "startTime", direction = Sort.Direction.ASC)
            Pageable pageable
    ) {
        log.info("GET /trips/allPaging HTTP/1.1");

        return tripService.getAllTripsPaging(pageable);
    }

    /**
     * 여행 등록 요청
     *
     * @param tripCreateRequest {@link TripCreateRequest} 여행 등록 요청 파라미터
     * @return {@link ResponseEntity}
     **/
    @PostMapping
    public ResponseEntity<ApiResponse> createTrip(@Valid @RequestBody TripCreateRequest tripCreateRequest) {
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
    public ResponseEntity<ApiResponse> updateTrip(@NotNull @PathVariable Long tripId, @Valid @RequestBody TripUpdateRequest tripUpdateRequest) {
        TripUpdateResponse savedTrip = tripService.updateTrip(tripId,tripUpdateRequest);
        return ResponseEntity.ok(ApiResponse.builder().status("Success").code(HttpStatus.OK.value()).data(savedTrip).build());

    }

    @PatchMapping("/{tripId}")
    public ResponseEntity<ApiResponse> PatchTrip(@NotNull @PathVariable Long tripId, @Valid @RequestBody TripPatchRequest tripPatchRequest) {
        TripPatchResponse savedTrip = tripService.patchTrip(tripId,tripPatchRequest);
        return ResponseEntity.ok(ApiResponse.builder().status("Success").code(HttpStatus.OK.value()).data(savedTrip).build());
    }



/**
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
    **/
}