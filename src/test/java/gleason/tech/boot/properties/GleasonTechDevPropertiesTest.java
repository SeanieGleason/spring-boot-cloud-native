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
@TestPropertySource("classpath:application-dev.properties")
public class GleasonTechDevPropertiesTest {

    @Autowired
    GleasonTechProperties gleasonTechProperties;

    @Test
    void testGetDevProperty() {
        assertEquals("Dev!", gleasonTechProperties.getProperty());
    }
}
