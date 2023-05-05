package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="features",glue= {"stepDefinitions"},
plugin= {"pretty","html:target/cucumber-reports/cucumber.html","json:target/cucumber-reports/cucumber.json"}
)
public class Runner {
	
}