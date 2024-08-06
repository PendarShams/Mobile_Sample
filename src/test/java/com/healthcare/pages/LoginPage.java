package com.healthcare.pages;


import com.healthcare.utils.MobileUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

    public class LoginPage {
        private AppiumDriver driver;

        @FindBy(id = "username")
        public WebElement usernameField;

        @FindBy(id = "password")
        public WebElement passwordField;

        @FindBy(id = "loginButton")
        public WebElement loginButton;

        @FindBy(xpath = "//h1[contains(text(), 'Welcome, [username]')]")
        public WebElement welcomeMessage;

        @FindBy(xpath = "//h1[contains(text(), 'Invalid credentials')]")
        public WebElement invalidCredentialsMessage;

        @FindBy(id = "appointment")
        public WebElement appointment;



        public LoginPage(AppiumDriver driver) {
            this.driver = driver;
            PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        }

        public void enterUsername(String username) {
            usernameField.sendKeys(username);
        }

        public void enterPassword(String password) {
            passwordField.sendKeys(password);
        }

        public void clickLoginButton() {
            loginButton.click();
        }

        public boolean isLoginSuccessful(String username) {

            return welcomeMessage.isDisplayed() && welcomeMessage.getText().contains(username);

        }

        public void navigateToModule(WebElement module) {
            MobileUtils.wait(5);
            module.click();
        }
    }


