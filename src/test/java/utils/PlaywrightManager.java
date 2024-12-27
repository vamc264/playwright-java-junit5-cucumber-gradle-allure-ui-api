package utils;

import com.microsoft.playwright.*;
import io.qameta.allure.Allure;
import org.junit.After;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class PlaywrightManager {
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;

    public static void multipleBrowserSetup(String browsername) {
        Playwright playwright = Playwright.create();
        switch (browsername) {
            case "chrome":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                        .setHeadless(false).setSlowMo(100));
                break;
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions()
                        .setHeadless(false).setSlowMo(100));
                break;
            case "webkit":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions()
                        .setHeadless(false).setSlowMo(100));
                break;
            default:
                System.out.println("No browser specified");
        }
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
        System.out.println("Closing context and browser...");
        context.close();
        browser.close();
    }

    public static String readFile(String filePath) {
        try {
            return Files.readString(Path.of(filePath));
        } catch (Exception e) {
            throw new RuntimeException("Failed to read file: " + filePath, e);
        }
    }
}