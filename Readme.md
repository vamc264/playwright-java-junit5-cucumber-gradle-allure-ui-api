
# Playwright Framework with Java, JUnit5, Gradle & Allure Reporting

## Table of Contents

  * [Overview](#overview)
  * [Prerequisites](#prerequisites)
    * [Tools and Versions](#tools-and-versions)
    * [Framework Features](#framework-features)
  * [Setup Instructions](#setup-instructions)
    * [Install Playwright](#install-playwright)
    * [Setup Gradle:](#setup-gradle)
    * [Configure Allure](#configure-allure)
    * [Set up Axe-Core for Accessibility Testing](#set-up-axe-core-for-accessibility-testing)
  * [Project Structure](#project-structure)
  * [Gradle Dependencies](#gradle-dependencies)
  * [Allure Reporting](#allure-reporting)
    * [Generate Allure Report](#generate-allure-report)
      * [Generate Report](#generate-report-)
      * [View Report](#view-report-)
      * [IntelliJ IDEA Integration](#intellij-idea-integration)
  * [Running Gradle Commands from Terminal](#running-gradle-commands-from-terminal)
    * [Common Commands](#common-commands)
  * [Notes](#notes)
  * [Screenshots](#screenshots)
    * [Overview](#overview-1)
    * [Features/Suites](#featuressuites)
    * [Graph](#graph)

## Overview
This project is a test automation framework based on the Playwright library. It is implemented using Java, with JUnit5 for test management, Allure for reporting, and Gradle for dependency management and build automation. The framework is compatible with IntelliJ IDEA for development and Git for version control.


## Prerequisites
Ensure you have the following installed:
### Tools and Versions
1. Java Development Kit (JDK): Version 11 or higher.
2. Gradle: Version 7.0 or higher.
3. IntelliJ IDEA: Latest version (Community or Ultimate Edition).
4. Git: Version control system.
5. Node.js: Required for Playwright installation.
6. Allure Commandline: For generating and displaying reports.
7. Axe-Core: For accessibility testing.

### Framework Features
* Playwright: Handles browser automation and interaction.
* JUnit5: Test lifecycle management and assertions.
* Allure Reporting: Generates visual reports for test execution.
* Gradle: Dependency management and build automation.
* Axe-Core: Integrates accessibility checks into the Playwright tests.


## Setup Instructions

### Install Playwright:
Run the following command to install Playwright browsers:

`npx playwright install`

### Setup Gradle:
Gradle is configured via `build.gradle` and `settings.gradle`. Follow the dependency section below to ensure everything is configured correctly.

### Configure Allure:
Ensure Allure command-line tools are installed on your machine. You can verify installation with:

`allure --version`

If not installed, you can add Allure using Homebrew (macOS) or Scoop (Windows).

`brew install allure` or `scoop install allure`

### Set up Axe-Core for Accessibility Testing
To integrate Axe-Core into your Playwright tests, follow these steps:

1. Download and Save the `axe.min.js` File Locally

```
   a. Open your browser and navigate to the CDN URL: https://cdnjs.cloudflare.com/ajax/libs/axe-core/4.7.2/axe.min.js

   b. Right-click and select Save As.

   c. Save the file in your project directory, e.g., src/test/resources/axe.min.js. 
```


2. Add Accessibility Test Helper Class: You can create a helper class to run accessibility tests using Axe-Core. 
Here's an example of a simple accessibility test:

```
import com.microsoft.playwright.*;
import com.deque.axe.Axe;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccessibilityTest {

    @Test
    public void testAccessibility() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        Page page = browser.newPage();

        // Navigate to the website to be tested
        page.navigate("https://example.com");

        // Run Axe-Core accessibility analysis
        Axe.Builder axeBuilder = new Axe.Builder(page);
        var results = axeBuilder.analyze();

        // Assert no violations
        assertTrue(results.getViolations().isEmpty(), "Accessibility violations found: " + results.getViolations());

        browser.close();
    }
}
```
3. Run Accessibility Tests: Use the standard Gradle test command to run accessibility tests along with other Playwright tests: 

`./gradlew clean cucumberTest allureReport allureServe`

Any accessibility violations will be outputted in the test report, and you can inspect them to ensure compliance with WCAG standards.


## Project Structure

```project-root
Sample Structure

|-- src
|   |-- main
|   |   |-- java (For main framework code, utilities)
|   |-- test
|       |-- java (For test cases)
|-- build.gradle (Gradle dependencies and configuration)
|-- allure-results (Generated Allure report files)
|-- README.md (Project documentation)
```

## Gradle Dependencies

The build.gradle file includes the following key dependencies(sample):
```
plugins {
    id 'java'
    id 'application'
}

dependencies {
    implementation 'com.microsoft.playwright:playwright:1.38.0'
    testImplementation 'org.junit.jupiter:junit-jupiter:5.10.0'
    testImplementation 'io.qameta.allure:allure-junit5:2.22.0'
}

tasks.named('test') {
    useJUnitPlatform()
}
```
## Allure Reporting
* Reports are found in the project under
`build/reports/allure-report`

### Generate Allure Report

#### Generate Report: 
1. Use the following command to generate the Allure report:

   `allure generate --clean allure-results`

#### View Report: 
2. Serve the report locally:

   `allure serve allure-results`

#### IntelliJ IDEA Integration
3. Install the Allure plugin for IntelliJ IDEA for direct report generation and viewing within the IDE.


## Running Gradle Commands from Terminal

### Common Commands

* Build the project:

  `gradle clean build`


* Run the tests using the following command without reporting:

   `./gradlew clean cucumberTest`


* Run the tests using the following command with reporting:

   `./gradlew clean cucumberTest allureReport allureServe`


* Check dependencies:

  `gradle dependencies`


## Notes

* Make sure to use the correct version of Java and Gradle to avoid compatibility issues.

* Regularly update dependencies in build.gradle to ensure compatibility with the latest features and fixes.

* Refer to the official documentation for more details on [Playwright](https://playwright.dev/), [JUnit5](https://junit.org/junit5/docs/current/user-guide/), [Allure](https://allurereport.org/docs/), and [Gradle](https://gradle.org/).

## Screenshots

### [Overview](screenshots/overview.png)
![](screenshots/overview.png)

### [Features/Suites](screenshots/suites.png)
![](screenshots/suites.png)

### [Graph](screenshots/graph.png)
![](screenshots/graph.png)






&copy; 2024 Playwright Web Application Test Automation