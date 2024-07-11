package org.Tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import org.TestMethods.TestModifyMethods;
import org.files.Payloads;
import org.utils.CommonMethods;
import org.testng.annotations.Test;
import org.TestMethods.TestGetMethods;

import static org.junit.Assert.*;

/**
 * author: Griscelda Plascencia
 */

public class TestCasesExamples extends TestGetMethods {
    final TestGetMethods testGetMethods = new TestGetMethods();
    final TestModifyMethods testModifyMethods = new TestModifyMethods();
    final Payloads payload = new Payloads();

    /**
     * This test is getting all information of the endpoint /posts.
     * Validates that statusCode matches expected
     */
    @Test
    public void shouldGetPostsInformation() {
        ValidatableResponse response = testGetMethods.getPosts();
        response.assertThat().statusCode(200);
    }

    /**
     * This test is getting information by the ID specified: /posts/{param}.
     * Validates that statusCode matches expected
     */
    @Test
    public void shouldGetPostsById() {
        String id = "2";
        ValidatableResponse response = testGetMethods.getPostsById(id);
        response.assertThat().statusCode(200);
    }

    /**
     * This test is NOT getting information by the ID specified: /posts/{param}
     *      as the resource ID does not exists.
     * Validates that statusCode matches expected
     */
    @Test
    public void shouldNotGetPostsById() {
        String id = "5000";
        ValidatableResponse response = testGetMethods.getPostsById(id);
        response.assertThat().statusCode(404);
    }

    /**
     * This test is getting information by the ID specified: /posts/{param}/comments
     * Validates that statusCode matches expected
     */
    @Test
    public void shouldGetPostsCommentsInformation() {
        String id = "3";
        ValidatableResponse response = testGetMethods.getPostsByIdComments(id);
        response.assertThat().statusCode(200);
    }

    /**
     * This test is getting information by the ID specified: /posts/{param}/comments
     * Validates that statusCode matches expected and the response is not null
     */
    @Test
    public void shouldGetPostsCommentsInformationAndNotNull() {
        String id = "4";
        ValidatableResponse response = testGetMethods.getPostsByIdComments(id);
        response.assertThat().statusCode(200);
        assertTrue("The response is not null or empty", CommonMethods.validateResponseIsNotNull(response));
    }

    /**
     * This test is NOT getting information by the ID specified: /posts/{param}/comments
     * Validates that the response is null
     */
    @Test
    public void shouldNotGetPostsCommentsInformationAsNull() {
        String id = "40000"; //This resource id is empty
        ValidatableResponse response = testGetMethods.getPostsByIdComments(id);
        assertFalse("The response is null or empty", CommonMethods.validateResponseIsNotNull(response));
    }

    /**
     * This test is getting information by postId as QueryParameter specified: /comments?postId={param}
     * Validates that statusCode matches expected
     */
    @Test
    public void shouldGetCommentsById() {
        String id = "6";
        ValidatableResponse response = testGetMethods.getCommentsByQueryParameter(id, "postId");
        response.assertThat().statusCode(200);
    }

    /**
     * This test is getting information by email as QueryParameter specified: /comments?email={param}
     * Validates that statusCode matches expected
     */
    @Test
    public void shouldGetCommentsByEmail() {
        String email = "Ronny@rosina.org";
        ValidatableResponse response = testGetMethods.getCommentsByQueryParameter(email, "email");
        response.assertThat().statusCode(200);
    }

    /**
     * This test is NOT getting information by email as QueryParameter specified: /comments?email={param}
     * Validates that statusCode matches expected
     */
    @Test
    public void shouldNotGetCommentsByEmail() {
        String email = "gris@g.test"; //This email is not registered
        ValidatableResponse response = testGetMethods.getCommentsByQueryParameter(email, "email");
        assertFalse("The response is null or empty", CommonMethods.validateResponseIsNotNull(response));
    }

    /**
     * This test is posting new record with specific payload into /posts/
     * Validates that statusCode matches expected and that response contains new title
     */
    @Test
    public void shouldPostNewRecord() {
        String titleToBePosted = "This is the title of new record to be posted";
        String bodyToBePosted =  "This is the body of new record to be posted";
        String userIdToBePosted = "900";

        String payloadToBeSend = String.format(payload.newRecord,
                userIdToBePosted, "888", titleToBePosted, bodyToBePosted);
        ValidatableResponse response = testModifyMethods.postPostsId(payloadToBeSend);
        response.assertThat().statusCode(201);

        JsonPath json = CommonMethods.parseJson(response.extract().asString());
        assertEquals(titleToBePosted, json.getString("title"));
    }

    /**
     * This test is updating through put a record by resourceNumber : /posts/{param}
     * Validates that statusCode matches expected and that response contains new body
     */
    @Test
    public void shouldPutInRecordById() {
        String titleToBeUpdated = "This is the title of record to be updated";
        String bodyToBeUpdated =  "This is the body of record to be updated";
        String resourceNumber = "2";

        String payloadToBeSend = String.format(payload.newRecord,
                resourceNumber, "888", titleToBeUpdated, bodyToBeUpdated);
        ValidatableResponse response = testModifyMethods.putPostsIdById(payloadToBeSend, resourceNumber);
        response.assertThat().statusCode(200);

        JsonPath json = CommonMethods.parseJson(response.extract().asString());
        assertEquals(bodyToBeUpdated, json.getString("body"));
    }

    /**
     * This test is NOT updating through put a record by resourceNumber : /posts/{param}
     *      as the record does not exist
     * Validates that statusCode matches expected
     */
    @Test
    public void shouldNotPutInRecordById() {
        String titleToBeUpdated = "This is the title of record to be updated";
        String bodyToBeUpdated =  "This is the body of record to be updated";
        String resourceNumber = "200000";

        String payloadToBeSend = String.format(payload.newRecord,
                resourceNumber, "888", titleToBeUpdated, bodyToBeUpdated);
        ValidatableResponse response = testModifyMethods.putPostsIdById(payloadToBeSend, resourceNumber);
        response.assertThat().statusCode(500);
    }

    /**
     * This test is deleting by resource Number: /posts/{param}
     * Validates that statusCode matches expected
     */
    @Test
    public void shouldDeleteRecordById() {
        String id = "2";
        ValidatableResponse response = testModifyMethods.deleteRecordById(id);
        response.assertThat().statusCode(200);
    }

}
