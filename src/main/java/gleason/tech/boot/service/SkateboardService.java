package gleason.tech.boot.service;

import gleason.tech.boot.model.CreateSkateboardDTO;
import gleason.tech.boot.model.Skateboard;
import gleason.tech.boot.model.SkateboardDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface SkateboardService {
    Skateboard getSkateboard(UUID id);

    Skateboard createSkateboard(CreateSkateboardDTO createSkateboardDTO);

    Skateboard updateSkateboard(SkateboardDTO skateboardDTO);

    Page<Skateboard> getSkateboards(Pageable pageable);

    void deleteSkateboard(UUID id);
}
