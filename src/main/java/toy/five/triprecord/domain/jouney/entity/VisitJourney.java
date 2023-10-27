package toy.five.triprecord.domain.jouney.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import toy.five.triprecord.domain.jouney.dto.journey_update.request.MoveJourneyUpdateRequest;
import toy.five.triprecord.domain.jouney.dto.journey_update.request.VisitJourneyUpdateRequest;
import toy.five.triprecord.domain.trip.entity.Trip;
import toy.five.triprecord.global.common.BaseTimeEntity;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class VisitJourney extends BaseJourney {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne  // mappedBy는 주인 쪽만
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JourneyType type;

    public void updateEntity(VisitJourneyUpdateRequest request) {
        this.name = request.getName();
        this.location = request.getLocation();
    }


}
