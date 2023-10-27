package toy.five.triprecord.domain.jouney.entity;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import toy.five.triprecord.global.common.BaseTimeEntity;
import java.time.LocalDateTime;

@Setter
@Getter
public class BaseJourney extends BaseTimeEntity {

    @Column
    private LocalDateTime startTime;

    @Column
    private LocalDateTime endTime;
}
