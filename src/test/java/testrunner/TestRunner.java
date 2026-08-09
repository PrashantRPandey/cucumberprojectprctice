package testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		// Using relative path instead of absolute C: drive path
		features = "src/test/resources/login.feature", glue = "stepdefination", plugin = { "pretty",
				"html:target/cucumber-reports.html" })
public class TestRunner extends AbstractTestNGCucumberTests {
}