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


    private <Req, E, Res> List<Res> saveJourneyList(Trip trip,
                                                    List<Req> requests,
                                                    BiFunction<Trip, Req, E> toEntity,
                                                    Function<E, Res> fromEntity,
                                                    JpaRepository<E, ?> repository) {
        if (requests != null) {
            List<E> entities = requests.stream()
                    .map(request -> toEntity.apply(trip, request))
                    .toList();
            List<E> savedEntities = repository.saveAll(entities);

            return savedEntities.stream()
                    .map(fromEntity)
                    .toList();
        }
        return new ArrayList<>();
    }


    @Override
    @Transactional
    public JourneyResponse saveJourneys(Long tripId, JourneyRequest request) {

        Trip trip = tripRepository.getReferenceById(tripId);

        List<MoveJourneyResponse> moveJourneyResponses = saveJourneyList(
                trip,
                request.getMoves(),
                (Trip t, MoveJourneyRequest r) -> r.toEntity(t),
                MoveJourneyResponse::fromEntity, moveJourneyRepository);





        List<VisitJourneyResponse> visitJourneyResponses = saveJourneyList(trip, request.getVisits(), (Trip t, VisitJourneyRequest r) -> r.toEntity(t), VisitJourneyResponse::fromEntity, visitJourneyRepository);
        List<LodgmentJourneyResponse> lodgmentJourneyResponses = saveJourneyList(trip, request.getLodgments(), (Trip t, LodgmentJourneyRequest r) -> r.toEntity(t), LodgmentJourneyResponse::fromEntity, lodgmentJourneyRepository);

        return JourneyResponse.of(moveJourneyResponses, visitJourneyResponses, lodgmentJourneyResponses);
    }


}
