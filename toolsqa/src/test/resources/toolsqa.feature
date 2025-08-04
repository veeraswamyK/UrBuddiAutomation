Feature: ToolsQaAutomation

  Scenario: To select the course which user wants to select under tutorials on homepage
    Given ToolsQa Application "https://toolsqa.com/" is launched and user is on home page
    When User click on user tutorials and selects the technology wanted to learn:
      | Front-End Testing Automation |
      | QA Practices                 |
      | Back-End Testing Automation  |
      | Mobile Testing Automation    |
      | Frameworks & Libraries       |
      | DevOps Tools                 |
      | Programming Language         |
      | Non-Functional Testing       |
      | Cross Browser Testing        |
    And User selects the tool to learn:
      | ISTQB Preparation |
      | Software Testing  |
      | Agile & Scrum     |
    Then Selected tool tutorial is opened


