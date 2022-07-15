package karate;

import com.intuit.karate.junit5.Karate;

class KarateIT {

    @Karate.Test
    Karate testAll() {
        return Karate.run().relativeTo(getClass());
    }
}