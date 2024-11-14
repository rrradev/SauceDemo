package com.saucelabs;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/com/saucelabs/features",
        plugin = {"pretty", "com.saucelabs.steps.config.hooks.GlobalHooks", "html:target/reports/cucumber-reports.html"})
public class TestRunner {
}
