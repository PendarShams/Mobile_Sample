 @smoke @ui
Feature: Login
  @login
  Scenario Outline: Successful Login
    Given the application is running
    When the user enters username "<user>"
    And the user enters password "<password>"
    And the user clicks the login button
    Then the user should be logged in successfully
    Examples:
    | user | password|
    | nurse | nurse123 |
    |doctor | doctor123 |
    | admin | admin123  |
@failed-login
  Scenario Outline: Failed Login - Empty Username
    When the user enters username "<user>"
    And the user clicks the login button
    Then the user should not be logged in
    And the user should see an error message saying "Username/Password is required"
    Examples:
      | user |
      | nurse |
      | doctor |
      | admin |

