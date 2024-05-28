package org.example.Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/org/example/Cucumber", glue="org/example/stepDefinations", monochrome = true,tags="@ErrorValidation",plugin = {"html:target/cucumber.html"})


public class cucumber_testNG_testRunner extends AbstractTestNGCucumberTests {
}
