package toy.five.triprecord.domain.trip.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.five.triprecord.domain.trip.validation.createtime.TripCreateTimeConstraint;


import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@TripCreateTimeConstraint
public class TripCreateRequest {

    @NotNull(message ="필드가 전부 채워져야 합니다.")
    @NotEmpty(message ="필드가 전부 채워져야 합니다.")
    @NotBlank(message ="필드가 전부 채워져야 합니다.")
    private String name;

    @NotNull(message ="시작 시간이  필요합니다.")
    private LocalDateTime startTime;

    @NotNull(message ="필드가 전부 채워져야 합니다.")
    private LocalDateTime endTime;

    @NotNull(message ="필드가 전부 채워져야 합니다.")
    private Boolean isDomestic;



}