package com.healthcare.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class APIUtils {

    /**
     * Return TOKEN as String by using provided username from /token endpoint
     * @param userType
     * @return
     */
    public static String getToken(String userType){


        String email=Config.getProperty(userType+"_username");
        String password="libraryUser";



        return getToken(email,password);


    }

    public static String getToken(String email,String password){


        return given()
                .contentType(ContentType.URLENC)
                .formParam("email" , email)
                .formParam("password" , password).
                when()
                .post(Config.getProperty("library.baseUri")+"/login")
                .prettyPeek()
                .then().statusCode(200)
                .extract().jsonPath().getString("token");

    }

    public static JSONObject getJSONFromAPI(String apiEndpointUrl, String user) {

        String token = getToken(user);

        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .get(apiEndpointUrl);

       // in case we dont need token: Response response = RestAssured.get(apiEndpointUrl);
        int statusCode = response.getStatusCode();

        if (statusCode == 200) {
            String jsonResponse = response.asString();
            return new JSONObject(jsonResponse);
        } else {
            throw new RuntimeException("API request failed with status code: " + statusCode);
        }


    }}




