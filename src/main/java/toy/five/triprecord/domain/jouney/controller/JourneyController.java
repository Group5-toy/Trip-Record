package toy.five.triprecord.domain.jouney.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import toy.five.triprecord.domain.jouney.dto.request.JourneyRequest;
import toy.five.triprecord.domain.jouney.dto.response.JourneyResponse;
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
    public JourneyResponse createJourney(@PathVariable Long tripId, @RequestBody JourneyRequest request) {
        return journeyService.saveJourneys(tripId, request);
    }

}
