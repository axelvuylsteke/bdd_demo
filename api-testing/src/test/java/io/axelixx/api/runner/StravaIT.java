package io.axelixx.api.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        tags = "@Strava and not @Ignore",
        glue = "io.axelixx.api",
        plugin = {
                "pretty",
                "json:target/cucumber/json/stravaResults.json",
                "html:target/cucumber/json/stravaResults.html"
        },
        snippets = CucumberOptions.SnippetType.CAMELCASE
)

public class StravaIT {
}
