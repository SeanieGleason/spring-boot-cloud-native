package karate;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Collection;
import java.util.stream.Collectors;

public class KarateParallelIT {

    private static void generateReport(String reportDir) {
        Collection<File> jsonFiles = FileUtils.listFiles(new File(reportDir), new String[]{"json"}, true);
        new ReportBuilder(
                jsonFiles.stream().map(File::getAbsolutePath).collect(Collectors.toList()),
                new Configuration(new File("build"), "skateboard"))
                .generateReports();
    }

    @Test
    void testParalell() {
        Results results = Runner.path("classpath:karate")
                .outputCucumberJson(true)
                .parallel(5);
        generateReport(results.getReportDir());
    }

}
