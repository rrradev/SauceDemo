import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features",
        plugin = {"pretty", "steps.config.GlobalHooks", "html:target/reports/cucumber-reports.html"})
public class TestRunner {
}
