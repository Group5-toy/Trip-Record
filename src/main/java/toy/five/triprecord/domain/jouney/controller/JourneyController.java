package toy.five.triprecord.domain.jouney.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import toy.five.triprecord.domain.jouney.dto.journey_create.request.JourneyCreateRequest;
import toy.five.triprecord.domain.jouney.dto.journey_create.response.JourneyCreateResponse;
import toy.five.triprecord.domain.jouney.dto.journey_update.request.JourneyUpdateRequest;
import toy.five.triprecord.domain.jouney.dto.journey_update.response.JourneyUpdateResponse;
import toy.five.triprecord.domain.jouney.service.JourneyService;

@RequiredArgsConstructor
@RequestMapping("/journey")
@RestController
public class JourneyController {

    private JourneyService journeyService;

    @Autowired
    public JourneyController(JourneyService journeyService) {
        this.journeyService = journeyService;
    }

    @PostMapping("/{tripId}")
    public JourneyCreateResponse createJourney(@PathVariable Long tripId, @RequestBody JourneyCreateRequest request) {
        return journeyService.saveJourneys(tripId, request);
    }

    @PutMapping("/{journeyId}/update")
    public JourneyUpdateResponse updateJourney(@PathVariable Long journeyId, @RequestBody JourneyUpdateRequest request) {
        return journeyService.modifyJourney(journeyId, request);
    }

}
