package stepdefinitions;

import com.microsoft.playwright.Page;
import io.cucumber.java.en.And;
import utils.AccessibilityChecker;
import utils.PlaywrightManager;

public class AccessibilitySteps {

    @And("the page should pass accessibility checks")
    public void thePageShouldPassAccessibilityChecks() {
        Page page = PlaywrightManager.getPage(); // Retrieve the Page from PlaywrightManager
        if (page == null) {
            throw new IllegalStateException("Page object is not initialized. Ensure the browser and page are set up.");
        }
        AccessibilityChecker.logAccessibilityViolations(page); // Perform accessibility checks
    }
}
