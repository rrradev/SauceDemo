import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "src/test/java/features")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, steps.config.GlobalHooks," +
        " html:target/reports/cucumber-reports.html")
public class TestRunner {
}
