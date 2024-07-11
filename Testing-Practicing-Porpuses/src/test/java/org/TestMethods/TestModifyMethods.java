package org.TestMethods;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.files.EndPoints;
import org.utils.CommonMethods;

import static io.restassured.RestAssured.given;

public class TestModifyMethods {
    final CommonMethods commonMethods = new CommonMethods();
    String baseURL = "https://jsonplaceholder.typicode.com/";

    public ValidatableResponse postPostsId(String payload) {
        RestAssured.baseURI= baseURL;
        return given().header("Content-Type", "application/json")
                .body(payload)
                .when().post(EndPoints.POSTS)
                .then().log().all();
    }

    public ValidatableResponse putPostsIdById(String payload, String parameterId) {
        RestAssured.baseURI= baseURL;
        return given().header("Content-Type", "application/json")
                .body(payload)
                .when().put(String.format(EndPoints.POSTPARAM, parameterId))
                .then().log().all();
    }

    public ValidatableResponse deleteRecordById(String id) {
        RestAssured.baseURI= baseURL;
        return given().header("Content-Type", "application/json")
                .when().delete(String.format(EndPoints.POSTPARAM, id))
                .then().log().all();
    }
}
