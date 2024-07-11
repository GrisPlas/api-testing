package org.utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

public class CommonMethods {

    public static JsonPath parseJson(String response) {
        JsonPath js = new JsonPath(response);
        return js;
    }

    public static boolean validateResponseIsNotNull(ValidatableResponse response) {
        String parsedResponse = response.extract().asString();
        if (!parsedResponse.isEmpty() && !parsedResponse.equals("[]"))
            return true;
        else
            return false;
    }
}
