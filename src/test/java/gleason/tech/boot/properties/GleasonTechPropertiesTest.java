package gleason.tech.boot.properties;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties(GleasonTechProperties.class)
@TestPropertySource("classpath:application.properties")
public class GleasonTechPropertiesTest {

    @Autowired
    GleasonTechProperties gleasonTechProperties;

    @Test
    void testGetDefaultProperty() {
        assertEquals("Default!", gleasonTechProperties.getProperty());
    }
}
