package toy.five.triprecord.domain.trip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toy.five.triprecord.domain.trip.dto.request.TripCreateRequest;
import toy.five.triprecord.domain.trip.dto.request.TripUpdateRequest;
import toy.five.triprecord.domain.trip.dto.response.TripCreateResponse;
import toy.five.triprecord.domain.trip.dto.response.TripUpdateResponse;
import toy.five.triprecord.domain.trip.service.TripService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trips")
public class TripController {


    private final TripService tripService;




    /**
     * 여행 등록 요청
     *
     * @param tripCreateRequest {@link TripCreateRequest} 여행 등록 요청 파라미터
     * @return {@link ResponseEntity}
     **/

    @PostMapping
    public ResponseEntity<TripCreateResponse> doRegisterASTrip(@RequestBody TripCreateRequest tripCreateRequest) {
        try {
            TripCreateResponse savedTrip = tripService.createTrip(tripCreateRequest);
            return ResponseEntity.ok(savedTrip);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * 여행 수정 요청
     *
     * @param tripId {@link Long} 여행글 ID 요청 파라미터
     * @param tripCreateRequest {@link TripCreateRequest} 여행 수정 내용 요청 파라미터
     * @return {@link ResponseEntity}
     **/


    @PutMapping("/{tripId}")
    public ResponseEntity<TripUpdateResponse> doRenewalAsTrip(@PathVariable Long tripId, @RequestBody TripUpdateRequest tripCreateRequest) {
        try {
            TripUpdateResponse savedTrip = tripService.updateTrip(tripId,tripCreateRequest);
            return ResponseEntity.ok(savedTrip);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).build();
        }

    }
}