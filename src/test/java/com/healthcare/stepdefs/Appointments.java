package com.healthcare.stepdefs;

import com.healthcare.pages.AppointmentsPage;
import com.healthcare.pages.LoginPage;
import com.healthcare.utils.APIUtils;
import com.healthcare.utils.AppiumDriverManager;
import com.healthcare.utils.DBUtils;
import com.healthcare.utils.MobileUtils;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import org.hamcrest.Matcher;
import org.openqa.selenium.WebElement;

import static com.healthcare.utils.AppiumDriverManager.driver;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Appointments {
LoginPage loginPage;
AppointmentsPage appointmentsPage;
public String username;

    @Given("the user enters username {string}")
    public void the_user_enters_username(String username) {
loginPage.enterUsername(username);
    }
    @Given("the user enters password {string}")
    public void the_user_enters_password(String password) {
loginPage.enterPassword(password);
    }
    @Given("the user clicks the login button")
    public void the_user_clicks_the_login_button() {
loginPage.clickLoginButton();
    }

    //--CREATE NEW APPOINTMENT
    @When("the user navigates to the appointment management page")
    public void the_user_navigates_to_the_appointment_management_page() {
        WebElement appointmentsModule = loginPage.appointment;
        loginPage.navigateToModule(appointmentsModule);

    }
    @When("the user schedules a new appointment")
    public void the_user_schedules_a_new_appointment() {
        appointmentsPage.scheduleNewAppointment();

    }
    @When("the user should be able to click and accept the terms and conditions")
    public void the_user_should_be_able_to_click_and_accept_the_terms_and_conditions() {
        appointmentsPage.acceptTermsAndConditions();

    }
    @Then("the appointment should be scheduled and displayed correctly")
    public void the_appointment_should_be_scheduled_and_displayed_correctly() {
        appointmentsPage.verifyAppointmentScheduled();

    }
    @Then("the appointment should be saved to the DataBase")
    public void the_appointment_should_be_saved_to_the_data_base() {
        //userid is same as the username
        String query = "SELECT *\n" +
                "FROM appointments\n" +
                "WHERE user_id = 'your_user_id'";
        DBUtils.runQuery(query);
        String Date = DBUtils.getCellValue(3, "Date");
        System.out.println("Schedules date = " + Date);
        assertThat(Date, allOf(notNullValue(), equalTo("your_scheduled_date")));

    }
    @Then("the appointment should be displayed in the API")
    public void the_appointment_should_be_displayed_in_the_api() {
        given().accept(ContentType.JSON)
                .pathParam("userID", "username")
                .header("Authorization", "Bearer " + APIUtils.getToken(username))
                .when().get("/api/users/your_user_id/appointments")
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .body("appointments.date", hasItem("your_scheduled_date"))
                .extract().response();
    }

   //--Delete Appointment

    @Given("an existing appointment scheduled")
    public void an_existing_appointment_scheduled() {
        loginPage.navigateToModule(loginPage.appointment);
        appointmentsPage.existingAppointment.click();
    }
    @When("the user deletes the appointment")
    public void the_user_deletes_the_appointment() {
        MobileUtils.wait(5);
        MobileUtils.swipe(driver, 500, 500, 500, 500, 2000);

    }
    @Then("the appointment should be removed from the list of appointments")
    public void the_appointment_should_be_removed_from_the_list_of_appointments() {
        assertThat(appointmentsPage.existingAppointment.isDisplayed(), is(false));
    }


    @Then("the user should receive a confirmation message")
    public void the_user_should_receive_a_confirmation_message() {
        MobileUtils.wait(5);
        driver.switchTo().alert().accept();

        String confirmationMessage = appointmentsPage.getConfirmationMessage();
        assertThat(confirmationMessage, is("Appointment deleted successfully"));
    }
    @Then("the appointment should not be displayed in DataBase")
    public void the_appointment_should_not_be_displayed_in_data_base() {
        //userid is same as the username
        String query = "SELECT *\n" +
                "FROM appointments\n" +
                "WHERE user_id = 'your_user_id'";
        DBUtils.runQuery(query);
        String Date = DBUtils.getCellValue(3, "Date");
        System.out.println("Schedules date = " + Date);
        assertThat(Date.isEmpty(), is(true) );

    }
    @Then("the appointment should not be displayed in API")
    public void the_appointment_should_not_be_displayed_in_api() {
        given().accept(ContentType.JSON)
                .pathParam("userID", "username")
                .header("Authorization", "Bearer " + APIUtils.getToken(username))
                .when().get("/api/users/your_user_id/appointments")
                .then().statusCode(404)
                .contentType(ContentType.JSON)
                .body("appointments.date", hasItem("your_scheduled_date"))
                .extract().response();
    }
}
