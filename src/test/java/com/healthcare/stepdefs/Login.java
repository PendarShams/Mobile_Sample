package com.healthcare.stepdefs;

import com.healthcare.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Login {
    private LoginPage loginPage;
    private String username;


    @Given("the application is running")
    public void theApplicationIsRunning() {
    }

    @When("the user enters username {string}")
    public void the_user_enters_username(String username) {
        loginPage.enterUsername(username);

    }
    @And("the user enters password {string}")
    public void theUserEntersPassword(String password) {
        loginPage.enterPassword(password);
    }
    @When("the user clicks the login button")
    public void the_user_clicks_the_login_button() {
        loginPage.clickLoginButton();
    }
    @Then("the user should be logged in successfully")
    public void the_user_should_be_logged_in_successfully() {
        loginPage.isLoginSuccessful(username);
    }

    @Then("the user should not be logged in")
    public void the_user_should_not_be_logged_in() {
        assertFalse(loginPage.isLoginSuccessful(username));

    }
    @Then("the user should see an error message saying {string}")
    public void the_user_should_see_an_error_message_saying(String string) {
        assertTrue(loginPage.invalidCredentialsMessage.isDisplayed());
    }



}
