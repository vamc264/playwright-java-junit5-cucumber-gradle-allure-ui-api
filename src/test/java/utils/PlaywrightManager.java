package utils;

import com.microsoft.playwright.*;
import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class PlaywrightManager {
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;

    public static void setup() {
        Playwright playwright = Playwright.create();
        browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(100));
        context = browser.newContext();
        page = context.newPage();
        page.waitForTimeout(3000);
    }

    public static Page getPage() {
        return page;
    }

    public static void captureScreenshot(String name) {
        byte[] screenshotBytes = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
        try (InputStream is = new ByteArrayInputStream(screenshotBytes)) {
            Allure.addAttachment(name, "image/png", is, "png");
        } catch (Exception e) {
            throw new RuntimeException("Failed to attach screenshot to Allure report", e);
        }
    }

    public static void tearDown() {
        context.close();
        browser.close();
    }
}