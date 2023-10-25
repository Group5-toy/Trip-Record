package toy.five.triprecord.domain.jouney.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.five.triprecord.domain.jouney.dto.request.JourneyRequest;
import toy.five.triprecord.domain.jouney.dto.request.LodgmentJourneyRequest;
import toy.five.triprecord.domain.jouney.dto.request.MoveJourneyRequest;
import toy.five.triprecord.domain.jouney.dto.request.VisitJourneyRequest;
import toy.five.triprecord.domain.jouney.dto.response.JourneyResponse;
import toy.five.triprecord.domain.jouney.dto.response.LodgmentJourneyResponse;
import toy.five.triprecord.domain.jouney.dto.response.MoveJourneyResponse;
import toy.five.triprecord.domain.jouney.dto.response.VisitJourneyResponse;
import toy.five.triprecord.domain.jouney.entity.LodgmentJourney;
import toy.five.triprecord.domain.jouney.entity.MoveJourney;
import toy.five.triprecord.domain.jouney.entity.VisitJourney;
import toy.five.triprecord.domain.jouney.repository.LodgmentJourneyRepository;
import toy.five.triprecord.domain.jouney.repository.MoveJourneyRepository;
import toy.five.triprecord.domain.jouney.repository.VisitJourneyRepository;
import toy.five.triprecord.domain.trip.entity.Trip;
import toy.five.triprecord.domain.trip.repository.TripRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JourneyServiceImpl implements JourneyService{

    private TripRepository tripRepository;
    private MoveJourneyRepository moveJourneyRepository;
    private VisitJourneyRepository visitJourneyRepository;
    private LodgmentJourneyRepository lodgmentJourneyRepository;

    @Autowired
    public JourneyServiceImpl(TripRepository tripRepository,
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

    @Override
    @Transactional
    public JourneyResponse saveJourneys(Long tripId, JourneyRequest request) {

        
        Trip trip = findTripById(tripId);

        List<MoveJourneyRequest> moveJourneyDtos = request.getMoves();//이동
        List<LodgmentJourneyRequest> lodgmentJourneyDtos = request.getLodgments();//숙박
        List<VisitJourneyRequest> visitJourneyDtos = request.getVisits();//체류

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

        List<MoveJourneyResponse> moveJourneyResponses =
                savedMoveJourneys.stream().map(MoveJourneyResponse::fromEntity).toList();
        List<LodgmentJourneyResponse> lodgmentJourneyResponses =
                savedLodgmentJourneys.stream().map(LodgmentJourneyResponse::fromEntity).toList();
        List<VisitJourneyResponse> visitJourneyResponses =
                savedVisitJourneys.stream().map(VisitJourneyResponse::fromEntity).toList();


        return JourneyResponse.of(moveJourneyResponses, visitJourneyResponses, lodgmentJourneyResponses);

    }
}
