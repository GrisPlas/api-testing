package org.TestMethods;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;
import org.files.EndPoints;

/**
 * author: Griscelda Plascencia
 */

public class TestGetMethods {
    String baseURL = "https://jsonplaceholder.typicode.com/";
    public ValidatableResponse getPosts() {
        RestAssured.baseURI= baseURL;
        return given().log().all().header("Content-Type", "application/json")
                .when().get(EndPoints.POSTS)
                .then().log().all();
    }

    public ValidatableResponse getPostsById(String parameter) {
        RestAssured.baseURI= baseURL;
        return  given().log().all().header("Content-Type", "application/json")
                .when().get(String.format(EndPoints.POSTPARAM, parameter))
                .then().log().all();
    }

    public ValidatableResponse getPostsByIdComments(String parameter) {
        RestAssured.baseURI= baseURL;
        return given().log().all().header("Content-Type", "application/json")
                .when().get(String.format(EndPoints.POSTPARAMCOMMENTS, parameter))
                .then().log().all();
    }

    public ValidatableResponse getCommentsByQueryParameter(String id, String queryParameter) {
        RestAssured.baseURI= baseURL;
        return given().queryParam(queryParameter,id).header("Content-Type", "application/json")
                .when().get(EndPoints.COMMENTS)
                .then().log().all();
    }

}