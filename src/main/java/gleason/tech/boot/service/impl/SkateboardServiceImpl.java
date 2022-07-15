package gleason.tech.boot.service.impl;

import gleason.tech.boot.exception.NotFoundException;
import gleason.tech.boot.mapping.SkateboardMapper;
import gleason.tech.boot.model.CreateSkateboardDTO;
import gleason.tech.boot.model.Skateboard;
import gleason.tech.boot.model.SkateboardDTO;
import gleason.tech.boot.repository.SkateboardRepository;
import gleason.tech.boot.service.SkateboardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SkateboardServiceImpl implements SkateboardService {

    SkateboardRepository skateboardRepository;
    SkateboardMapper skateboardMapper;

    public SkateboardServiceImpl(SkateboardRepository skateboardRepository, SkateboardMapper skateboardMapper) {
        this.skateboardRepository = skateboardRepository;
        this.skateboardMapper = skateboardMapper;
    }

    @Override
    public Skateboard getSkateboard(UUID id) {
        return skateboardRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public Page<Skateboard> getSkateboards(Pageable pageable) {
        return skateboardRepository.findAll(pageable);
    }

    @Override
    public void deleteSkateboard(UUID id) {
        skateboardRepository.deleteById(id);
    }

    @Override
    public Skateboard createSkateboard(CreateSkateboardDTO createSkateboardDTO) {
        return skateboardRepository.save(skateboardMapper.convert(createSkateboardDTO));
    }

    @Override
    public Skateboard updateSkateboard(SkateboardDTO skateboardDTO) {
        return skateboardRepository.save(skateboardMapper.convert(skateboardDTO));
    }

}
