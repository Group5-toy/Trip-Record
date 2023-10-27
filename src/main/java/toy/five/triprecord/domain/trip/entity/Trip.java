package toy.five.triprecord.domain.trip.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import toy.five.triprecord.domain.jouney.entity.LodgmentJourney;
import toy.five.triprecord.domain.jouney.entity.MoveJourney;
import toy.five.triprecord.domain.jouney.entity.VisitJourney;
import toy.five.triprecord.domain.trip.dto.request.TripCreateRequest;
import toy.five.triprecord.domain.trip.dto.request.TripUpdateRequest;
import toy.five.triprecord.domain.trip.dto.response.TripUpdateResponse;
import toy.five.triprecord.global.common.BaseTimeEntity;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Trip extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "trip", cascade = CascadeType.ALL)
    private List<MoveJourney> moveJourneys;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "trip", cascade = CascadeType.ALL)
    private List<VisitJourney> visitJourneys;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "trip", cascade = CascadeType.ALL)
    private List<LodgmentJourney> lodgmentJourneys;

    @Column
    private LocalDateTime startTime;

    @Column
    private LocalDateTime endTime;

    @Column
    private Boolean isDomestic;

    private void updateName(String name) {
        this.name = name;
    }

    private void updateStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    private void updateEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    private void updateIsDomestic(Boolean isDomestic) {
        this.isDomestic = isDomestic;
    }

    public void updateColumns(TripUpdateRequest tripUpdateRequest) {
        updateName(tripUpdateRequest.getName());
        updateStartTime(tripUpdateRequest.getStartTime());
        updateEndTime(tripUpdateRequest.getEndTime());
        updateIsDomestic(tripUpdateRequest.getIsDomestic());
    }










}