package toy.five.triprecord.domain.jouney.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import toy.five.triprecord.domain.jouney.dto.journey_create.request.JourneyCreateRequest;
import toy.five.triprecord.domain.jouney.dto.journey_create.request.LodgmentJourneyCreateRequest;
import toy.five.triprecord.domain.jouney.dto.journey_create.request.MoveJourneyCreateRequest;
import toy.five.triprecord.domain.jouney.dto.journey_create.request.VisitJourneyCreateRequest;
import toy.five.triprecord.domain.jouney.dto.journey_create.response.JourneyCreateResponse;
import toy.five.triprecord.domain.jouney.dto.journey_update.request.LodgmentJourneyUpdateRequest;
import toy.five.triprecord.domain.jouney.dto.journey_update.request.MoveJourneyUpdateRequest;
import toy.five.triprecord.domain.jouney.dto.journey_update.request.VisitJourneyUpdateRequest;
import toy.five.triprecord.domain.jouney.dto.journey_update.response.LodgmentJourneyUpdateResponse;
import toy.five.triprecord.domain.jouney.dto.journey_update.response.MoveJourneyUpdateResponse;
import toy.five.triprecord.domain.jouney.dto.journey_update.response.VisitJourneyUpdateResponse;
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
    public JourneyCreateResponse createJourney(
            @PathVariable Long tripId,
            @RequestBody JourneyCreateRequest request
    ) {
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

        LodgmentJourneyCreateRequest lodgment =
                LodgmentJourneyCreateRequest.builder()
                        .name("숙박11")
                        .dormitoryName("야놀자호텔")
                        .type(LODGMENT)
                        .build();

        VisitJourneyCreateRequest visit =
                VisitJourneyCreateRequest.builder()
                        .name("체류11")
                        .location("관악구")
                        .type(VISIT)
                        .build();

        JourneyCreateRequest journeyRequest = JourneyCreateRequest.builder()
                .moves(List.of(move))
                .lodgments(List.of(lodgment))
                .visits(List.of(visit))
                .build();

        journeyService.saveJourneys(1L, journeyRequest);

    }

}
