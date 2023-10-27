package toy.five.triprecord.domain.jouney.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import toy.five.triprecord.domain.jouney.dto.journey_update.request.MoveJourneyUpdateRequest;
import toy.five.triprecord.domain.trip.entity.Trip;
import toy.five.triprecord.global.common.BaseTimeEntity;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class MoveJourney extends BaseJourney {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String vehicle;

    @Column(nullable = false)
    private String startPoint;

    @Column(nullable = false)
    private String endPoint;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JourneyType type;

    // null 값 채킹 필요
    public void updateEntity(MoveJourneyUpdateRequest request) {
        this.name = request.getName();
        this.vehicle = request.getVehicle();
        this.startPoint = request.getStartPoint();
        this.endPoint = request.getEndPoint();
    }

}
