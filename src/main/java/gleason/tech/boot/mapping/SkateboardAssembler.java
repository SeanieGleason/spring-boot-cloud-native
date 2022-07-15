package gleason.tech.boot.mapping;

import gleason.tech.boot.controller.SkateboardController;
import gleason.tech.boot.model.Skateboard;
import gleason.tech.boot.model.SkateboardDTO;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SkateboardAssembler extends RepresentationModelAssemblerSupport<Skateboard, SkateboardDTO> {

    SkateboardMapper skateboardMapper;

    public SkateboardAssembler(SkateboardMapper skateboardMapper) {
        super(SkateboardController.class, SkateboardDTO.class);
        this.skateboardMapper = skateboardMapper;
    }

    @Override
    public SkateboardDTO toModel(Skateboard skateboard) {
        SkateboardDTO skateboardDTO = skateboardMapper.convert(skateboard);
        skateboardDTO.add(linkTo(methodOn(SkateboardController.class).getSkateboard(skateboard.getId())).withSelfRel());
        return skateboardDTO;
    }
}
