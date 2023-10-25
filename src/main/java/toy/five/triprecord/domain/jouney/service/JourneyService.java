package toy.five.triprecord.domain.jouney.service;

import toy.five.triprecord.domain.jouney.dto.request.JourneyRequest;
import toy.five.triprecord.domain.jouney.dto.response.JourneyResponse;

public interface JourneyService {

    JourneyResponse saveJourneys(Long tripId, JourneyRequest request);

}
