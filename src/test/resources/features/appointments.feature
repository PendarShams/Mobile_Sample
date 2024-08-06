@smoke @appointment-management
Feature: Appointments
@ui @db @api @create-appointment
  Scenario Outline: Create Appointment
    Given the user enters username "<user>"
    And the user enters password "<password>"
    And the user clicks the login button
    When the user navigates to the appointment management page
    And the user schedules a new appointment
    And the user should be able to click and accept the terms and conditions
    Then the appointment should be scheduled and displayed correctly
    And the appointment should be saved to the DataBase
    And the appointment should be displayed in the API
    Examples:
      | user | password|
      | nurse | nurse123 |
      |doctor | doctor123 |
      | admin | admin123  |

@ui @db @api @delete-appointment
Scenario Outline: Delete Appointment
  Given the user enters username "<user>"
  And the user enters password "<password>"
  And the user clicks the login button
 And an existing appointment scheduled
  When the user deletes the appointment
  Then the appointment should be removed from the list of appointments
  And the user should receive a confirmation message
  And the appointment should not be displayed in DataBase
  And the appointment should not be displayed in API
  Examples:
    | user | password|
    | nurse | nurse123 |
    |doctor | doctor123 |
    | admin | admin123  |