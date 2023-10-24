package toy.five.triprecord.domain.jouney.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.five.triprecord.global.common.BaseTimeEntity;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Journey extends BaseTimeEntity {

    //fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
