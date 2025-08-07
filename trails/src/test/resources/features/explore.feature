Feature: urbuddiExploration

  @login
  Scenario: Login to urbuddi with valid credentials
    Given urbuddi loginpage is launched
    When User enters valid data from "veeraswamy.kalluri@optimworks.com"and"1234567"
    Then User should enter the status of the test case

  @logout
  Scenario: To logout employee successfully
    Given urbuddi loginpage is launched
    When User enters valid data from "veeraswamy.kalluri@optimworks.com"and"1234567"
    And Click on logout on dashboard and yes on prompt
    Then urbuddi loginpage is displayed again for login

  @signup
  Scenario:To create a new employee successfully
    Given urbuddi loginpage is launched
    When User enters valid data from "veeraswamy.kalluri@optimworks.com"and"1234567"
    And User click on "Employees" in dashboard
    And User click on "Add Employee"
    And Fill the "firstName,lastName,id,email,role,password,dob,joiningDate,qualifications,department,gender,mobileNumber,designation,bloodGroup,salary,location,reportingTo,Certificates" details and click on add
    Then User able to save Employee successfully
