Feature: Login to urbuddi
 Scenario Outline: users are able to login urbuddi
   Given Urbuddi is launched "<url>"
   When user enter "<username>" and "<password>"
   Then user login succesfully
   Examples:
     |username  |  password|url|
     |veera|  swampy@123 |https://dev.urbuddi.com/login|
     |veer|  sway@13  |https://dev.urbuddi.com/login|
     |veers|  say@123  |https://dev.urbuddi.com/login|
     |via|  swam@123  |https://dev.urbuddi.com/login|
