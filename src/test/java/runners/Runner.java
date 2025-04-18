package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;



    @RunWith(Cucumber.class)
    @CucumberOptions(
            plugin = {
                    "pretty"
            },
            features = "src/test/resources/features",
            glue = {"stepdefinitions"},
            tags = "@End2End",
            dryRun = false
            )
    public class Runner {}
