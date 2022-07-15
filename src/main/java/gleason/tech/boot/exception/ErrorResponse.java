package gleason.tech.boot.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Schema(name = "Error")
public class ErrorResponse {

    private final UUID id = UUID.randomUUID();
    private final LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("UTC"));
    private String message;
    private int httpStatus;

}
