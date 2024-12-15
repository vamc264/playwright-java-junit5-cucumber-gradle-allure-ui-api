Feature: Search for a word on Google

  Scenario: Search for a keyword and validate results
    Given I open the Google homepage
    When I search for "Playwright Java"
    Then I validate that the search results contain "Playwright"
    When I send a GET request to URL
    Then the response contains the key "word" with value "playwright"
