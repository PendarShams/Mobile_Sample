@smoke
Feature: User Authorization
@authorization @ui @api
  Scenario Outline: Verify User Authorization
    Given the user enters username "<user>"
    And the user enters password "<password>"
    And the user clicks the login button
    When the user should be logged in successfully
    Then the user should see their name and state displayed on module in top right
    And the user should see a list of available procedures
  And the UI list should match API
Examples:
  | user      | password |
  | nurse  | nurse123    |
  | doctor | doctor123 |
  | patient | patient123 |
  | admin  | admin123    |