package io.axelixx.api.runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        tags = "@Tryout and not @Ignore",
        glue = "io.axelixx.api",
        plugin = {"pretty",
                "json:target/cucumber/json/tryoutResults.json",
                "html:target/cucumber/html/tryoutResults.html"},
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class TryoutIT {
}
