package com.healthcare.pages;

import com.healthcare.utils.MobileUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AppointmentsPage {

    @FindBy(id = "scheduleAppointmentButton")
    public WebElement scheduleAppointmentButton;

    @FindBy(id = "termsAndConditionsCheckbox")
    public WebElement termsAndConditionsCheckbox;

    @FindBy(id = "appointmentStatus")
    public WebElement appointmentStatus;

    @FindBy(id = "appointmentDateBox")
    public WebElement appointmentDateBox;

    @FindBy(id = "appointmentTimeBox")
    public WebElement appointmentTimeBox;

    @FindBy(id = "enterButtonForAppointment")
    public WebElement enterButtonForAppointment;

@FindBy(id ="existingAppointment")
    public WebElement existingAppointment;

    @FindBy(id = "confirmationMessage")
    public WebElement confirmationMessage;



    public void scheduleNewAppointment() {
        scheduleAppointmentButton.click();
        MobileUtils.wait(2);
        appointmentDateBox.sendKeys("your_scheduled_date");
        appointmentTimeBox.sendKeys("your_scheduled_time");
        enterButtonForAppointment.click();
        MobileUtils.wait(2);
    }

    public void acceptTermsAndConditions() {
        termsAndConditionsCheckbox.click();
        MobileUtils.wait(2);
    }

    public void verifyAppointmentScheduled() {

        MobileUtils.wait(5);
        String expectedStatus = "Scheduled";
        String actualStatus = appointmentStatus.getText();

        if (!expectedStatus.equals(actualStatus)) {
            throw new AssertionError("Appointment status is not scheduled. Expected: " + expectedStatus + ", Actual: " + actualStatus);
        }
    }

    public String getConfirmationMessage() {
        return confirmationMessage.getText();
    }
}
