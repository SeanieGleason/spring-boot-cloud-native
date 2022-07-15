package gleason.tech.boot.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.ZonedDateTime;
import java.util.UUID;

@Schema(name = "Skateboard")
@AllArgsConstructor
@Data
@NoArgsConstructor
@Relation(collectionRelation = "skateboards", itemRelation = "skateboard")
public class SkateboardDTO extends RepresentationModel<SkateboardDTO> {

    private UUID id;

    private String name;
    private double width;
    private double length;
    private ZonedDateTime createdDate;
    private ZonedDateTime lastModifiedDate;
    private String lateModifiedBy;

}