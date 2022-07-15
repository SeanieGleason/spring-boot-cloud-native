package gleason.tech.boot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gleason.tech.boot.mapping.SkateboardAssembler;
import gleason.tech.boot.mapping.SkateboardMapper;
import gleason.tech.boot.model.CreateSkateboardDTO;
import gleason.tech.boot.model.Skateboard;
import gleason.tech.boot.model.SkateboardDTO;
import gleason.tech.boot.service.SkateboardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = SkateboardController.class)
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
public class SkateboardControllerTest {

    private static final String ID = UUID.randomUUID().toString();
    private static final String NAME = "toy machine";
    private static final Double WIDTH = 7.75;
    private static final Double LENGTH = 7.75;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SkateboardService skateboardService;
    @Autowired
    private SkateboardAssembler skateboardAssembler;
    @Autowired
    private SkateboardMapper skateboardMapper;

    @Test
    void testGetSkateboard() throws Exception {

        Skateboard skateboard = new Skateboard(UUID.fromString(ID), NAME, WIDTH, LENGTH, null, null, null);

        when(skateboardService.getSkateboard(any(UUID.class))).thenReturn(skateboard);

        mockMvc.perform(
                        get("/v1/skateboard/{id}", ID)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.width").value(WIDTH))
                .andExpect(jsonPath("$.length").value(LENGTH))
                .andDo(document("getASkateboard"));

    }

    @Test
    void testGetSkateboards() throws Exception {

        Skateboard skateboard = new Skateboard(UUID.fromString(ID), NAME, WIDTH, LENGTH, null, null, null);

        when(skateboardService.getSkateboards(any(Pageable.class))).thenReturn(new PageImpl<>(Collections.singletonList(skateboard)));

        mockMvc.perform(
                        get("/v1/skateboards")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.skateboards[0].id").value(ID))
                .andExpect(jsonPath("$._embedded.skateboards[0].name").value(NAME))
                .andExpect(jsonPath("$._embedded.skateboards[0].width").value(WIDTH))
                .andExpect(jsonPath("$._embedded.skateboards[0].length").value(LENGTH))
                .andDo(document("getASkateboard"));

    }

    @Test
    void testPostSkateboard() throws Exception {

        CreateSkateboardDTO createSkateboardDTO = new CreateSkateboardDTO(NAME, WIDTH, LENGTH);
        Skateboard skateboard = new Skateboard(UUID.fromString(ID), NAME, WIDTH, LENGTH, null, null, null);

        when(skateboardService.createSkateboard(any(CreateSkateboardDTO.class))).thenReturn(skateboard);

        mockMvc.perform(
                        post("/v1/skateboard")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(createSkateboardDTO))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.width").value(WIDTH))
                .andExpect(jsonPath("$.length").value(LENGTH))
                .andDo(document("createSkateboard"));

    }

    @Test
    void testPutSkateboard() throws Exception {

        SkateboardDTO skateboardDTO = new SkateboardDTO(UUID.fromString(ID), NAME, WIDTH, LENGTH, null, null, null);

        when(skateboardService.updateSkateboard(any(SkateboardDTO.class))).thenReturn(skateboardMapper.convert(skateboardDTO));

        mockMvc.perform(
                        put("/v1/skateboard/{id}", ID)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(skateboardDTO))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.width").value(WIDTH))
                .andExpect(jsonPath("$.length").value(LENGTH))
                .andDo(document("updateSkateboard"));

    }

    @Test
    void testDeleteSkateboard() throws Exception {

        doNothing().when(skateboardService).deleteSkateboard(any(UUID.class));

        mockMvc.perform(
                        delete("/v1/skateboard/{id}", ID)
                )
                .andDo(print())
                .andExpect(status().isNoContent())
                .andDo(document("deleteSkateboard"));
    }

    @TestConfiguration
    public static class SkateboardControllerTestConfiguration {
        @Bean
        SkateboardMapper skateboardMapper() {
            return Mappers.getMapper(SkateboardMapper.class);
        }

        @Bean
        SkateboardAssembler skateboardAssembler(SkateboardMapper skateboardMapper) {
            return new SkateboardAssembler(skateboardMapper);
        }

        @Bean
        ObjectMapper objectMapper() {
            return new ObjectMapper();
        }
    }
}
