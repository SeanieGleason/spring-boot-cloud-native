package gleason.tech.boot.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("gleason.tech")
public class GleasonTechProperties {
    String property;
}
