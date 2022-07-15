package gleason.tech.boot.config;

import gleason.tech.boot.mapping.SkateboardMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean
    public SkateboardMapper skateboardMapper() {
        return Mappers.getMapper(SkateboardMapper.class);
    }
}
