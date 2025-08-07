Feature: ToolsQaAutomation

  Scenario: To select the course which user wants to select under tutorials on homepage
    Given ToolsQa Application "https://toolsqa.com/" is launched and user is on home page
    When User click on user tutorials and selects the technology wanted to learn "QA Practices"
    And User selects the tool to learn "Software Testing"
    Then Selected tool tutorial is opened


