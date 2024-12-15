package stepdefinitions;

import com.microsoft.playwright.Page;
import utils.PlaywrightManager;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoogleSearchSteps {
    private Page page;

    @Given("I open the Google homepage")
    public void iOpenTheGoogleHomepage() {
        PlaywrightManager.setup();
        page = PlaywrightManager.getPage();
        page.navigate("https://www.google.com");
    }

    @When("I search for {string}")
    public void iSearchFor(String searchTerm) {
        page.click("//*[@id=\"W0wltc\"]");
        page.click("//*[@id=\"APjFqb\"]");
        page.fill("//*[@id=\"APjFqb\"]", searchTerm);
        page.keyboard().press("Enter");
        page.waitForTimeout(3000);
    }

    @Then("I validate that the search results contain {string}")
    public void iValidateThatTheSearchResultsContain(String expectedText) {
        try {
            boolean containsText = page.locator("#search").innerText().contains(expectedText);
            assertTrue(containsText, "Search results do not contain the expected text.");
        } catch (AssertionError e) {
            PlaywrightManager.captureScreenshot("GoogleSearchFailure.png");
            throw e;
        } finally {
            PlaywrightManager.tearDown();
        }
    }
}