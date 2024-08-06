package com.healthcare.stepdefs;

import com.healthcare.pages.LoginPage;
import com.healthcare.pages.PatientRecordPage;
import com.healthcare.utils.AppiumDriverManager;
import com.healthcare.utils.DBUtils;
import com.healthcare.utils.MobileUtils;
import io.appium.java_client.AppiumBy;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class PatientRecord {

String username;
LoginPage loginPage;
PatientRecordPage patientRecordPage;

    @When("the user navigates to the patient record page")
    public void the_user_navigates_to_the_patient_record_page() {
loginPage.navigateToModule(patientRecordPage.patientRecordTab);
    }
    @Then("the patient records should be displayed correctly with DB")
    public void the_patient_records_should_be_displayed_correctly_with_DB() {
        //checking the number of displayed records in UI
        List<WebElement> rows = AppiumDriverManager.driver.findElements(AppiumBy.xpath("tableXPath" + "/tbody/tr"));
        int numberOfRowsUI = rows.size();
//checking the number of records in DB
        String query = "SELECT COUNT(*) AS record_count\n" +
                "FROM records\n" +
                "WHERE user_id = 'your_user_id'";

        DBUtils.runQuery(query);
        int numberOfRowsDB = DBUtils.getSingleValueInt("record_count");

        // Perform assertions or further actions based on the number of rows
        assertThat(numberOfRowsUI, is(equalTo(numberOfRowsDB)));



    }
}
