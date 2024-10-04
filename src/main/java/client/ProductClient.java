package client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.request.PostProductRequestModel;

import static io.restassured.RestAssured.given;

public class ProductClient extends BaseClient {

    private final String PRODUCT = "/produtos";
    private final String PRODUCT_BY_ID = "/produtos/{_id}";

    public Response registerProduct(PostProductRequestModel product, String token) {
        return
                given()
                        .spec(super.set())
                        .contentType(ContentType.JSON)
                        .header("Authorization", token)
                        .body(product)
                .when()
                        .post(PRODUCT)
                ;
    }

    public Response getAllProducts() {
        return
                given()
                        .spec(super.set())
                .when()
                        .get(PRODUCT)
                ;
    }

    public Response getProductById(String id) {
        return
                given()
                        .spec(super.set())
                        .pathParams("_id", id)
                .when()
                        .get(PRODUCT_BY_ID)
                ;
    }

    public Response updateProduct(String id, PostProductRequestModel product, String token) {
        return
                given()
                        .spec(super.set())
                        .contentType(ContentType.JSON)
                        .pathParams("_id", id)
                        .header("Authorization", token)
                        .body(product)
                .when()
                        .put(PRODUCT_BY_ID)
                ;
    }

    public Response deleteProduct(String id, String token) {
        return
                given()
                        .spec(super.set())
                        .contentType(ContentType.JSON)
                        .header("Authorization", token)
                        .pathParams("_id", id)
                .when()
                        .delete(PRODUCT_BY_ID)
                ;
    }

}
