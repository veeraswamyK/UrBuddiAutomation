Feature: Flipkart Login

  @login
  Scenario: Login to Flipkart with valid credentials
    Given Flipkart homepage is launched
    When User enters valid "veeraswamy.kalluri@optimworks.com" and "1234567"
    Then User should be logged in successfully
