Feature: Search for a word on Google

  @chrome @firefox @webkit
  Scenario Outline: Search for a keyword and validate results
    Given I open the Google homepage in "<browserName>"
    When I search for "Playwright Java"
    Then I validate that the search results contain "Playwright"
    When I send a GET request to URL
    Then the response contains the key "word" with value "playwright"
    Examples:
      | browserName |
#      | chrome      |
      | firefox     |
      | webkit      |
