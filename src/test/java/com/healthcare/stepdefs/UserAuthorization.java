package com.healthcare.stepdefs;

import com.healthcare.pages.UserAuthorizationPage;
import io.cucumber.java.en.Then;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;

public class UserAuthorization {
    String user;
    UserAuthorizationPage userAuthorizationPage;
    List<String> availableProceduresUI;

    @Then("the user should see their name and state displayed on module in top right")
    public void the_user_should_see_their_name_and_state_displayed_on_module_in_top_right() {
            user = userAuthorizationPage.userName.getText();
            String state = userAuthorizationPage.userState.getText();

        assertThat("User Name is displayed", userAuthorizationPage.userName.isDisplayed());
        assertThat("User State is displayed", userAuthorizationPage.userState.isDisplayed());

            System.out.println("User Name: " + user);
            System.out.println("User State: " + state);
        }

    @Then("the user should see a list of available procedures")
    public void the_user_should_see_a_list_of_available_procedures() {

        availableProceduresUI = userAuthorizationPage.getAvailableProceduresUI();
        assertThat(availableProceduresUI, not(empty()));
        System.out.println("Available Procedures: " + availableProceduresUI);
    }

    @Then("the UI list should match API")
    public void the_ui_list_should_match_api() {
        List<String> availableProceduresAPI = userAuthorizationPage.getAvailableProceduresAPI(user);

        if (availableProceduresUI.equals(availableProceduresAPI)) {
            System.out.println("UI list matches DB and API");
        } else {
            System.out.println("UI list does not match DB and API");
        }
    }
}
