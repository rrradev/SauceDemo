Feature: Login

  Background:
    Given the user is on the Login Page

#######################################################################
  Scenario: The user can see the login form
    Then he/she should see the login form
#######################################################################
  Scenario Outline: Log in with incorrect credentials
    When the user tries to log in with incorrect or expired credentials:
      | username   | password   |
      | <username> | <password> |
    Then he should see the correct error:
      | error   |
      | <error> |
    Examples:
      | username        | password     | error                                                       |
      | [empty]         | [empty]      | Username is required                                        |
      | [empty]         | secret_sauce | Username is required                                        |
      | standard_user   | [empty]      | Password is required                                        |
      | SAERSADA        | KLASDd       | Username and password do not match any user in this service |
      | locked_out_user | secret_sauce | Sorry, this user has been locked out.                       |
#######################################################################

