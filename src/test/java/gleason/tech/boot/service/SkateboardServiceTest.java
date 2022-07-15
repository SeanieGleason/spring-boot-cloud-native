package gleason.tech.boot.service;

import gleason.tech.boot.exception.NotFoundException;
import gleason.tech.boot.mapping.SkateboardMapper;
import gleason.tech.boot.model.CreateSkateboardDTO;
import gleason.tech.boot.model.Skateboard;
import gleason.tech.boot.model.SkateboardDTO;
import gleason.tech.boot.repository.SkateboardRepository;
import gleason.tech.boot.service.impl.SkateboardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class SkateboardServiceTest {

    private static final UUID ID = UUID.randomUUID();

    @MockBean
    SkateboardRepository skateboardRepository;

    @Autowired
    SkateboardMapper skateboardMapper;

    private SkateboardService skateboardService;

    @BeforeEach
    public void beforeEach() {
        skateboardService = new SkateboardServiceImpl(skateboardRepository, skateboardMapper);
    }

    @Test
    void testGetSkateboard() {
        Skateboard skateboard = new Skateboard(ID, null, Double.NaN, Double.NaN, null, null, null);

        when(skateboardRepository.findById(any(UUID.class))).thenReturn(Optional.of(skateboard));

        Skateboard serviceSkateboard = skateboardService.getSkateboard(ID);

        assertEquals(skateboard, serviceSkateboard);
    }

    @Test
    void testGetSkateboard_NotFoundException() {
        when(skateboardRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            skateboardService.getSkateboard(ID);
        });

    }

    @Test
    void testGetSkateboards() {
        Skateboard skateboard = new Skateboard(ID, null, Double.NaN, Double.NaN, null, null, null);

        when(skateboardRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(Collections.singletonList(skateboard)));

        Page<Skateboard> skateboards = skateboardService.getSkateboards(PageRequest.of(0, 10));

        assertEquals(skateboard, skateboards.getContent().get(0));
    }

    @Test
    void testCreateSkateboard() {
        CreateSkateboardDTO createSkateboardDTO = new CreateSkateboardDTO(null, Double.NaN, Double.NaN);
        Skateboard skateboard = new Skateboard(ID, null, Double.NaN, Double.NaN, null, null, null);

        when(skateboardRepository.save(any(Skateboard.class))).thenReturn(skateboard);

        Skateboard serviceSkateboard = skateboardService.createSkateboard(createSkateboardDTO);

        assertEquals(ID, serviceSkateboard.getId());
    }

    @Test
    void testUpdateSkateboard() {
        SkateboardDTO skateboardDTO = new SkateboardDTO(ID, null, Double.NaN, Double.NaN, null, null, null);

        when(skateboardRepository.save(any(Skateboard.class))).thenReturn(skateboardMapper.convert(skateboardDTO));

        Skateboard serviceSkateboard = skateboardService.updateSkateboard(skateboardDTO);

        assertEquals(ID, serviceSkateboard.getId());
    }

    @Test
    void testDeleteSkateboard() {
        doNothing().when(skateboardRepository).deleteById(any(UUID.class));

        skateboardService.deleteSkateboard(ID);

        verify(skateboardRepository, times(1)).deleteById(any(UUID.class));
    }

    @TestConfiguration
    public static class SkateboardServiceTestConfiguration {

        @Bean
        SkateboardMapper skateboardMapper() {
            return Mappers.getMapper(SkateboardMapper.class);
        }
    }
}
