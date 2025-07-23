Feature: Create an employee
  Background:
    Given Urbuddi is launched throuh the url "https://dev.urbuddi.com/login"
    When User login  using the user name  "veeraswamy.kalluri@optimworks.com" and "1234567"
  Scenario:To create a new employee successfully
    Given User is already logged in
    When User click on "Employees" in dashboard
    And User click on "Add Employee"
    And Fill the "firstName,lastName,id,email,role,password,dob,joiningDate,qualifications,department,gender,mobileNumber,designation,bloodGroup,salary,location,reportingTo,Certificates" details and click on add
    Then User able to save Employee successfully

