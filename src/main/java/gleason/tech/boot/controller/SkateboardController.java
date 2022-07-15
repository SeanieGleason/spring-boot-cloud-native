package gleason.tech.boot.controller;

import gleason.tech.boot.mapping.SkateboardAssembler;
import gleason.tech.boot.model.CreateSkateboardDTO;
import gleason.tech.boot.model.Skateboard;
import gleason.tech.boot.model.SkateboardDTO;
import gleason.tech.boot.service.SkateboardService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("v1")
@RestController
public class SkateboardController {

    SkateboardService skateboardService;
    SkateboardAssembler skateboardAssembler;

    public SkateboardController(SkateboardService skateboardService, SkateboardAssembler skateboardAssembler) {
        this.skateboardService = skateboardService;
        this.skateboardAssembler = skateboardAssembler;
    }

    @GetMapping("skateboard/{id}")
    public SkateboardDTO getSkateboard(@PathVariable UUID id) {
        return skateboardAssembler.toModel(skateboardService.getSkateboard(id));
    }

    @GetMapping("skateboards")
    @Parameter(name = "page")
    @Parameter(name = "size")
    @Parameter(name = "sort")
    public PagedModel<SkateboardDTO> getSkateboards(@Parameter(hidden = true) PagedResourcesAssembler<Skateboard> pagedResourcesAssembler,
                                                    @Parameter(hidden = true) @PageableDefault Pageable pageable) {
        return pagedResourcesAssembler.toModel(skateboardService.getSkateboards(pageable), skateboardAssembler);
    }

    @PostMapping("skateboard")
    public SkateboardDTO createSkateboard(@RequestBody CreateSkateboardDTO createSkateboardDTO) {
        return skateboardAssembler.toModel(skateboardService.createSkateboard(createSkateboardDTO));
    }

    @PutMapping("skateboard/{id}")
    public SkateboardDTO putSkateboard(@PathVariable UUID id, @RequestBody SkateboardDTO skateboardDTO) {
        skateboardDTO.setId(id);
        return skateboardAssembler.toModel(skateboardService.updateSkateboard(skateboardDTO));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("skateboard/{id}")
    public void deleteSkateboard(@PathVariable UUID id) {
        skateboardService.deleteSkateboard(id);
    }

}
