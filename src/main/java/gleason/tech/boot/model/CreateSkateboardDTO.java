package gleason.tech.boot.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "NewSkateboard")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class CreateSkateboardDTO {

    private String name;
    private double width;
    private double length;

}