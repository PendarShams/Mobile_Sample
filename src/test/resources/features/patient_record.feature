@smoke
Feature: Patient Record
@patient-record @ui @db
  Scenario Outline: View Patient Record
    Given the user enters username "<user>"
    And the user enters password "<password>"
    And the user clicks the login button
    When the user navigates to the patient record page
    Then the patient records should be displayed correctly with DB
Examples:
    | user      | password |
    | nurse  | nurse123    |
    | doctor | doctor123 |
    | patient | patient123 |
    | admin  | admin123    |