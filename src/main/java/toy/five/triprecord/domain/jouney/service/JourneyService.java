package toy.five.triprecord.domain.jouney.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.five.triprecord.domain.jouney.dto.journey_create.request.JourneyCreateRequest;
import toy.five.triprecord.domain.jouney.dto.journey_create.request.LodgmentJourneyCreateRequest;
import toy.five.triprecord.domain.jouney.dto.journey_create.request.MoveJourneyCreateRequest;
import toy.five.triprecord.domain.jouney.dto.journey_create.request.VisitJourneyCreateRequest;
import toy.five.triprecord.domain.jouney.dto.journey_create.response.JourneyCreateResponse;
import toy.five.triprecord.domain.jouney.dto.journey_create.response.LodgmentJourneyCreateResponse;
import toy.five.triprecord.domain.jouney.dto.journey_create.response.MoveJourneyCreateResponse;
import toy.five.triprecord.domain.jouney.dto.journey_create.response.VisitJourneyCreateResponse;
import toy.five.triprecord.domain.jouney.dto.journey_update.request.JourneyUpdateRequest;
import toy.five.triprecord.domain.jouney.dto.journey_update.request.LodgmentJourneyUpdateRequest;
import toy.five.triprecord.domain.jouney.dto.journey_update.request.MoveJourneyUpdateRequest;
import toy.five.triprecord.domain.jouney.dto.journey_update.request.VisitJourneyUpdateRequest;
import toy.five.triprecord.domain.jouney.dto.journey_update.response.JourneyUpdateResponse;
import toy.five.triprecord.domain.jouney.dto.journey_update.response.LodgmentJourneyUpdateResponse;
import toy.five.triprecord.domain.jouney.dto.journey_update.response.MoveJourneyUpdateResponse;
import toy.five.triprecord.domain.jouney.dto.journey_update.response.VisitJourneyUpdateResponse;
import toy.five.triprecord.domain.jouney.entity.LodgmentJourney;
import toy.five.triprecord.domain.jouney.entity.MoveJourney;
import toy.five.triprecord.domain.jouney.entity.VisitJourney;
import toy.five.triprecord.domain.jouney.repository.LodgmentJourneyRepository;
import toy.five.triprecord.domain.jouney.repository.MoveJourneyRepository;
import toy.five.triprecord.domain.jouney.repository.VisitJourneyRepository;
import toy.five.triprecord.domain.trip.entity.Trip;
import toy.five.triprecord.domain.trip.repository.TripRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JourneyService {

    private TripRepository tripRepository;
    private MoveJourneyRepository moveJourneyRepository;
    private VisitJourneyRepository visitJourneyRepository;
    private LodgmentJourneyRepository lodgmentJourneyRepository;

    @Autowired
    public JourneyService(TripRepository tripRepository,
                          MoveJourneyRepository moveJourneyRepository,
                          VisitJourneyRepository visitJourneyRepository,
                          LodgmentJourneyRepository lodgmentJourneyRepository) {
        this.tripRepository = tripRepository;
        this.moveJourneyRepository = moveJourneyRepository;
        this.visitJourneyRepository = visitJourneyRepository;
        this.lodgmentJourneyRepository = lodgmentJourneyRepository;
    }

    private Trip findTripById(Long tripId) {
        return tripRepository.findById(tripId)
                .orElseThrow(RuntimeException::new);
    }
    
    @Transactional
    public JourneyCreateResponse saveJourneys(Long tripId, JourneyCreateRequest request) {

        
        Trip trip = findTripById(tripId);

        List<MoveJourneyCreateRequest> moveJourneyDtos = request.getMoves();//이동
        List<LodgmentJourneyCreateRequest> lodgmentJourneyDtos = request.getLodgments();//숙박
        List<VisitJourneyCreateRequest> visitJourneyDtos = request.getVisits();//체류

        List<MoveJourney> moveJourneys =
                moveJourneyDtos.stream().map(journeyRequest ->
                                MoveJourney.builder()
                                    .trip(findTripById(tripId))
                                    .name(journeyRequest.getName())
                                    .vehicle(journeyRequest.getVehicle())
                                    .startPoint(journeyRequest.getStartPoint())
                                    .endPoint(journeyRequest.getEndPoint())
                                    .type(journeyRequest.getType())
                                    .build()
                        ).toList();

        List<LodgmentJourney> lodgmentJourneys =
                lodgmentJourneyDtos.stream().map(journeyRequest ->
                        LodgmentJourney.builder()
                                .trip(findTripById(tripId))
                                .name(journeyRequest.getName())
                                .dormitoryName(journeyRequest.getDormitoryName())
                                .type(journeyRequest.getType())
                                .build()
                        ).toList();

        List<VisitJourney> visitJourneys =
                visitJourneyDtos.stream().map(journeyRequest ->
                        VisitJourney.builder()
                                .trip(findTripById(tripId))
                                .name(journeyRequest.getName())
                                .location(journeyRequest.getLocation())
                                .type(journeyRequest.getType())
                                .build()
                        ).toList();

        List<MoveJourney> savedMoveJourneys =
                moveJourneyRepository.saveAll(moveJourneys);
        List<LodgmentJourney> savedLodgmentJourneys =
                lodgmentJourneyRepository.saveAll(lodgmentJourneys);
        List<VisitJourney> savedVisitJourneys =
                visitJourneyRepository.saveAll(visitJourneys);

        List<MoveJourneyCreateResponse> moveJourneyCreateResponses =
                savedMoveJourneys.stream().map(MoveJourneyCreateResponse::fromEntity).toList();
        List<LodgmentJourneyCreateResponse> lodgmentJourneyCreateResponses =
                savedLodgmentJourneys.stream().map(LodgmentJourneyCreateResponse::fromEntity).toList();
        List<VisitJourneyCreateResponse> visitJourneyCreateResponses =
                savedVisitJourneys.stream().map(VisitJourneyCreateResponse::fromEntity).toList();


        return JourneyCreateResponse.of(moveJourneyCreateResponses, visitJourneyCreateResponses, lodgmentJourneyCreateResponses);
    }

    @Transactional
    public JourneyUpdateResponse modifyJourney(Long journeyId, JourneyUpdateRequest request) {

        if(request.getMoves().isEmpty() && !request.getVisits().isEmpty() && !request.getLodgments().isEmpty()) {
            // error 처리
        } else if (!request.getMoves().isEmpty()) {
            System.out.println("선택됨!");
            MoveJourney existMoveJourney = moveJourneyRepository.getReferenceById(journeyId);
            MoveJourneyUpdateRequest updateRequest = request.getMoves().get(0);
            MoveJourneyUpdateRequest existRequest  = MoveJourneyUpdateRequest.builder()
                    .name(existMoveJourney.getName())
                    .vehicle(existMoveJourney.getVehicle())
                    .startPoint(existMoveJourney.getStartPoint())
                    .endPoint(existMoveJourney.getEndPoint())
                    .build();
            existRequest.updateCheck(updateRequest);
            existMoveJourney.updateEntity(updateRequest);
            return JourneyUpdateResponse.of(Collections.singletonList(MoveJourneyUpdateResponse.fromEntity(existMoveJourney)), null, null);
        } else if (!request.getVisits().isEmpty()) {
            VisitJourney existVisitJourney = visitJourneyRepository.getReferenceById(journeyId);
            VisitJourneyUpdateRequest updateRequest = request.getVisits().get(0);
            VisitJourneyUpdateRequest existRequest  = VisitJourneyUpdateRequest.builder()
                    .name(existVisitJourney.getName())
                    .location(existVisitJourney.getLocation())
                    .build();
            existRequest.updateCheck(updateRequest);
            existVisitJourney.updateEntity(updateRequest);
            return JourneyUpdateResponse.of(null, Collections.singletonList(VisitJourneyUpdateResponse.fromEntity(existVisitJourney)), null);
        } else if (!request.getLodgments().isEmpty()) {
            LodgmentJourney existLodgmentJourney = lodgmentJourneyRepository.getReferenceById(journeyId);
            LodgmentJourneyUpdateRequest updateRequest = request.getLodgments().get(0);
            LodgmentJourneyUpdateRequest existRequest  = LodgmentJourneyUpdateRequest.builder()
                    .name(existLodgmentJourney.getName())
                    .dormitoryName(existLodgmentJourney.getDormitoryName())
                    .build();
            existRequest.updateCheck(updateRequest);
            existLodgmentJourney.updateEntity(updateRequest);
            return JourneyUpdateResponse.of(null, null, Collections.singletonList(LodgmentJourneyUpdateResponse.fromEntity(existLodgmentJourney)));
        }

        return JourneyUpdateResponse.of(null, null, null);
    }
}
