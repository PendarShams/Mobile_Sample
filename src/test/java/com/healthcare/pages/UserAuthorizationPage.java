package com.healthcare.pages;

import com.healthcare.utils.APIUtils;
import com.healthcare.utils.AppiumDriverManager;
import com.healthcare.utils.Config;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class UserAuthorizationPage {

    String procedure;
AppiumDriver driver;

@FindBy(id = "user_name")
    public WebElement userName;

@FindBy(id = "user_state")
    public WebElement userState;

    public UserAuthorizationPage(AppiumDriver driver) {
        this.driver = driver;
    }

    public List<String> getAvailableProceduresUI() {
        List<String> availableProcedures = new ArrayList<>();

        // Assuming the list of procedures is displayed in a scrollable view with a unique identifier
        // Replace the following code with your actual UI element locator
        List<WebElement> procedureElements = AppiumDriverManager.driver.findElements(AppiumBy.id("procedure_list_item"));

        for (WebElement procedureElement : procedureElements) {
            // Assuming each procedure element has a child element with the procedure name
            // Replace the following code with your actual UI element locator
            String procedureName = procedureElement.findElement(AppiumBy.id("procedure_name")).getText();
            availableProcedures.add(procedureName);
        }

        return availableProcedures;
    }

    public List<String> getAvailableProceduresAPI(String user) {
        List<String> availableProcedures = new ArrayList<>();

        // Assuming the API endpoint returns a JSON response with an array of procedure names
        // Replace the following code with your actual API endpoint URL
        String apiEndpointUrl = Config.getProperty("apiEndpointUrl");

       JSONObject responseJson = APIUtils.getJSONFromAPI(apiEndpointUrl,user);

        if (responseJson != null && responseJson.has("procedures")) {
            JSONArray proceduresArray = responseJson.getJSONArray("procedures");

            for (int i = 0; i < proceduresArray.length(); i++) {
                String procedureName = proceduresArray.getString(i);
                availableProcedures.add(procedureName);
            }
        }

        return availableProcedures;

    }
public void selectProcedure(String procedure) {
    procedure = procedure.toLowerCase();
    // Assuming the procedure is selected by clicking on a specific element
    // Assuming each procedure element has a child element with the procedure name
    // Replace the following code with your actual UI element locator
    WebElement procedureElement = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='" + procedure + "']"));
    procedureElement.click();
}

}