package client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.request.AuthRequestModel;

import static io.restassured.RestAssured.given;

public class LoginClient extends BaseClient {

    private final String LOGIN = "/login";

    public Response login(AuthRequestModel credentials) {
        return
                given()
                        .spec(super.set())
                        .contentType(ContentType.JSON)
                        .body(credentials)
                .when()
                        .post(LOGIN)
                ;
    }
}