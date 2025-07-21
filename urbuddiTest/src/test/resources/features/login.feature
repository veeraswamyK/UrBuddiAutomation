Feature: Login to urbuddi via scenario outline

  Scenario Outline: Login with multiple credentials
    Given Urbuddi is launched "https://dev.urbuddi.com/login"
    When I login with "<username>" and "<password>"
    Then user login successfully

    Examples:
      | username | password |
      | veeraswamy.kalluri@optimworks.com | 1234567 |
      | veeraswamy.kalluri@optimworks.com | 1234567 |
      | veeraswamy.kalluri@optimworks.com | 1234567 |
      | veeraswamy.kalluri@optimworks.com | 1234567 |
      | kebfkb | 545454 |
      | fenwekjn@123.com | jhdfvkk |
