Feature: Login Functionality
@smoke
  Scenario: Login with valid user credentials
    Given Urbuddi is launched "https://dev.urbuddi.com/login"
    When I login with "veeraswamy.kalluri@optimworks.com" and "1234567"
    Then User login successfully
