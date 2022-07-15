package gleason.tech.boot.mapping;

import gleason.tech.boot.model.CreateSkateboardDTO;
import gleason.tech.boot.model.Skateboard;
import gleason.tech.boot.model.SkateboardDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SkateboardMapper {

    SkateboardDTO convert(Skateboard skateboard);

    Skateboard convert(SkateboardDTO skateboardDTO);

    Skateboard convert(CreateSkateboardDTO createSkateboardDTO);

}
