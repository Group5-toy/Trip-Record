package toy.five.triprecord.domain.jouney.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import toy.five.triprecord.domain.jouney.dto.request.JourneyRequest;
import toy.five.triprecord.domain.jouney.dto.request.LodgmentJourneyRequest;
import toy.five.triprecord.domain.jouney.dto.request.MoveJourneyRequest;
import toy.five.triprecord.domain.jouney.dto.request.VisitJourneyRequest;
import toy.five.triprecord.domain.jouney.dto.response.JourneyResponse;
import toy.five.triprecord.domain.jouney.entity.JourneyType;
import toy.five.triprecord.domain.jouney.service.JourneyService;

import java.util.List;

import static toy.five.triprecord.domain.jouney.entity.JourneyType.*;

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

    @PutMapping("/move/{journeyId}")
    public MoveJourneyUpdateResponse updateMoveJourney(
            @PathVariable Long journeyId,
            @RequestBody MoveJourneyUpdateRequest updateRequest
    ) {
        return journeyService.modifyMoveJourney(journeyId, updateRequest);
    }

    @PutMapping("/lodgment/{journeyId}")
    public LodgmentJourneyUpdateResponse updateLodgmentJourney(
            @PathVariable Long journeyId,
            @RequestBody LodgmentJourneyUpdateRequest updateRequest
    ) {
        return journeyService.modifyLodgmentJourney(journeyId, updateRequest);
    }

    @PutMapping("/visit/{journeyId}")
    public VisitJourneyUpdateResponse updateVisitJourney(
            @PathVariable Long journeyId,
            @RequestBody VisitJourneyUpdateRequest updateRequest
    ) {
        return journeyService.modifyVisitJourney(journeyId, updateRequest);
    }



    @PostConstruct
    public void init() {
        MoveJourneyCreateRequest move =
                MoveJourneyCreateRequest.builder()
                        .name("이동11")
                        .vehicle("대중 교통")
                        .startPoint("서울")
                        .endPoint("대전")
                        .type(MOVE)
                        .build();

        LodgmentJourneyRequest lodgment =
                LodgmentJourneyRequest.builder()
                        .name("숙박11")
                        .dormitoryName("야놀자호텔")
                        .type(LODGMENT)
                        .build();

        VisitJourneyRequest visit =
                VisitJourneyRequest.builder()
                        .name("체류11")
                        .location("관악구")
                        .type(VISIT)
                        .build();

        JourneyRequest journeyRequest = JourneyRequest.builder()
                .moves(List.of(move))
                .lodgments(List.of(lodgment))
                .visits(List.of(visit))
                .build();

        journeyService.saveJourneys(1L, journeyRequest);

    }

}
