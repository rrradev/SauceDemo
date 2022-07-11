package steps.config;

import io.cucumber.java8.LambdaGlue;
import io.cucumber.java8.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks extends BaseSteps implements LambdaGlue {

    public Hooks() {
        After((Scenario scenario) -> {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot");
        });

    }
}
