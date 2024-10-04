package client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.request.PostUserRequestModel;

import static io.restassured.RestAssured.given;

public class UserClient extends BaseClient {

    private final String USER = "/usuarios";
    private final String USER_BY_ID = "/usuarios/{_id}";

    public Response registerUser(PostUserRequestModel user) {
        return
                given()
                        .spec(super.set())
                        .contentType(ContentType.JSON)
                        .body(user)
                .when()
                        .post(USER)
                ;
    }

    public Response getAllUsers() {
        return
                given()
                        .spec(super.set())
                .when()
                        .get(USER)
                ;
    }

    public Response getUserById(String id) {
        return
                given()
                        .spec(super.set())
                        .pathParams("_id", id)
                .when()
                        .get(USER_BY_ID)
                ;
    }

    public Response updateUser(String id, PostUserRequestModel user) {
        return
                given()
                        .spec(super.set())
                        .contentType(ContentType.JSON)
                        .pathParams("_id", id)
                        .body(user)
                .when()
                        .put(USER_BY_ID)
                ;
    }

    public Response deleteUser(String id) {
        return
                given()
                        .spec(super.set())
                        .contentType(ContentType.JSON)
                        .pathParams("_id", id)
                .when()
                        .delete(USER_BY_ID)
                ;
    }

}
