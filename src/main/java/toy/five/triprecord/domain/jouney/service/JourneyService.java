package toy.five.triprecord.domain.jouney.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.five.triprecord.domain.jouney.dto.JourneysDetailResponse;
import toy.five.triprecord.domain.jouney.dto.LodgmentJourneyDetailResponse;
import toy.five.triprecord.domain.jouney.dto.MoveJourneyDetailResponse;
import toy.five.triprecord.domain.jouney.dto.VisitJourneyDetailResponse;
import toy.five.triprecord.domain.jouney.dto.journey_create.request.JourneyCreateRequest;
import toy.five.triprecord.domain.jouney.dto.journey_create.request.LodgmentJourneyCreateRequest;
import toy.five.triprecord.domain.jouney.dto.journey_create.request.MoveJourneyCreateRequest;
import toy.five.triprecord.domain.jouney.dto.journey_create.request.VisitJourneyCreateRequest;
import toy.five.triprecord.domain.jouney.dto.journey_create.response.JourneyCreateResponse;
import toy.five.triprecord.domain.jouney.dto.journey_create.response.LodgmentJourneyCreateResponse;
import toy.five.triprecord.domain.jouney.dto.journey_create.response.MoveJourneyCreateResponse;
import toy.five.triprecord.domain.jouney.dto.journey_create.response.VisitJourneyCreateResponse;
import toy.five.triprecord.domain.jouney.dto.journey_update.request.LodgmentJourneyUpdateRequest;
import toy.five.triprecord.domain.jouney.dto.journey_update.request.MoveJourneyUpdateRequest;
import toy.five.triprecord.domain.jouney.dto.journey_update.request.VisitJourneyUpdateRequest;
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

import java.util.List;
import java.util.stream.Stream;

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

    @Transactional(readOnly = true)
    public JourneysDetailResponse getAllJourneysByTripId(Long tripId) {
        List<MoveJourneyDetailResponse> moveResponses = moveJourneyRepository.findAllByTripId(tripId)
                .stream().map(MoveJourneyDetailResponse::fromEntity).toList();
        List<LodgmentJourneyDetailResponse> lodgmentResponses = lodgmentJourneyRepository.findAllByTripId(tripId)
                .stream().map(LodgmentJourneyDetailResponse::fromEntity).toList();
        List<VisitJourneyDetailResponse> visitResponses = visitJourneyRepository.findAllByTripId(tripId)
                .stream().map(VisitJourneyDetailResponse::fromEntity).toList();

        return JourneysDetailResponse.of(moveResponses, visitResponses, lodgmentResponses);

    }

    private Trip findTripById(Long tripId) {
        return tripRepository.findById(tripId)
                .orElseThrow(RuntimeException::new);
    }
    
    @Transactional
    public JourneyCreateResponse saveJourneys(Long tripId, JourneyCreateRequest request) {

        List<MoveJourneyCreateRequest> moveJourneyDtos = request.getMoves();//이동
        List<LodgmentJourneyCreateRequest> lodgmentJourneyDtos = request.getLodgments();//숙박
        List<VisitJourneyCreateRequest> visitJourneyDtos = request.getVisits();//체류

        // 여기다 출발시간, 도착 시간 추가 필요
        List<MoveJourney> moveJourneys =
                moveJourneysToReponses(tripId, moveJourneyDtos);
        List<LodgmentJourney> lodgmentJourneys =
                lodgmentJourneysToResponses(tripId, lodgmentJourneyDtos);
        List<VisitJourney> visitJourneys = 
                visitJourneysToResponses(tripId, visitJourneyDtos);

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

        return JourneyCreateResponse.of(
                moveJourneyCreateResponses,
                visitJourneyCreateResponses,
                lodgmentJourneyCreateResponses
        );
    }

    private List<VisitJourney> visitJourneysToResponses(Long tripId, List<VisitJourneyCreateRequest> visitJourneyDtos) {
        List<VisitJourney> visitJourneys =
                visitJourneyDtos.stream().map(journeyRequest ->
                        VisitJourney.builder()
                                .trip(findTripById(tripId))
                                .name(journeyRequest.getName())
                                .location(journeyRequest.getLocation())
                                .type(journeyRequest.getType())
                                .build()
                        ).toList();
        return visitJourneys;
    }

    private List<LodgmentJourney> lodgmentJourneysToResponses(Long tripId, List<LodgmentJourneyCreateRequest> lodgmentJourneyDtos) {
        List<LodgmentJourney> lodgmentJourneys =
                lodgmentJourneyDtos.stream().map(journeyRequest ->
                        LodgmentJourney.builder()
                                .trip(findTripById(tripId))
                                .name(journeyRequest.getName())
                                .dormitoryName(journeyRequest.getDormitoryName())
                                .type(journeyRequest.getType())
                                .build()
                        ).toList();
        return lodgmentJourneys;
    }

    private List<MoveJourney> moveJourneysToReponses(Long tripId, List<MoveJourneyCreateRequest> moveJourneyDtos) {
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
        return moveJourneys;
    }

    @Transactional
    public MoveJourneyUpdateResponse modifyMoveJourney (
            Long journeyId,
            MoveJourneyUpdateRequest updateRequest
    ){
        MoveJourney findJourney = moveJourneyRepository.findById(journeyId)
                .orElseThrow(RuntimeException::new);    //.orElseThrow(()->CustomException(ERRORCODE.NO_EXIST)

        findJourney.setName(updateRequest.getName());
        findJourney.setVehicle(updateRequest.getVehicle());
        findJourney.setStartPoint(updateRequest.getStartPoint());
        findJourney.setEndPoint(updateRequest.getEndPoint());
        findJourney.setStartTime(updateRequest.getStartTime());
        findJourney.setEndTime(updateRequest.getEndTime());

        return MoveJourneyUpdateResponse.fromEntity(findJourney);

    }

    @Transactional
    public LodgmentJourneyUpdateResponse modifyLodgmentJourney (
            Long journeyId,
            LodgmentJourneyUpdateRequest updateRequest
    ){
        LodgmentJourney findJourney = lodgmentJourneyRepository.findById(journeyId)
                .orElseThrow(RuntimeException::new);    //.orElseThrow(()->CustomException(ERRORCODE.NO_EXIST)

        findJourney.setName(updateRequest.getName());
        findJourney.setDormitoryName(updateRequest.getDormitoryName());
        findJourney.setStartTime(updateRequest.getStartTime());
        findJourney.setEndTime(updateRequest.getEndTime());

        return LodgmentJourneyUpdateResponse.fromEntity(findJourney);

    }

    @Transactional
    public VisitJourneyUpdateResponse modifyVisitJourney (
            Long journeyId,
            VisitJourneyUpdateRequest updateRequest
    ){
        VisitJourney findJourney = visitJourneyRepository.findById(journeyId)
                .orElseThrow(RuntimeException::new);    //.orElseThrow(()->CustomException(ERRORCODE.NO_EXIST)

        findJourney.setName(updateRequest.getName());
        findJourney.setLocation(updateRequest.getLocation());
        findJourney.setStartTime(updateRequest.getStartTime());
        findJourney.setEndTime(updateRequest.getEndTime());

        return VisitJourneyUpdateResponse.fromEntity(findJourney);

    }


}
