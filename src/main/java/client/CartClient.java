package client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.request.PostCartRequestModel;

import static io.restassured.RestAssured.given;

public class CartClient extends BaseClient {

    private final String CART = "/carrinhos";
    private final String CART_BY_ID = "/carrinhos/{_id}";
    private final String CART_DELETE_CANCEL = "/carrinhos/cancelar-compra";
    private final String CART_DELETE_BUY = "/carrinhos/concluir-compra";

    public Response postCart(PostCartRequestModel cart, String token) {
        return
                given()
                        .spec(super.set())
                        .contentType(ContentType.JSON)
                        .header("Authorization", token)
                        .body(cart)
                .when()
                    .post(CART)
                ;
    }

    public Response getAllCarts() {
        return
                given()
                        .spec(super.set())
                .when()
                        .get(CART)
                ;
    }

    public Response getCartById(String id) {
        return
                given()
                        .spec(super.set())
                        .pathParams("_id", id)
                .when()
                        .get(CART_BY_ID)
                ;
    }

    public Response deleteCartBuy(String token) {
        return
                given()
                        .spec(super.set())
                        .contentType(ContentType.JSON)
                        .header("Authorization", token)
                .when()
                        .delete(CART_DELETE_BUY)
                ;
    }

    public Response deleteCartCancel(String token) {
        return
                given()
                        .spec(super.set())
                        .contentType(ContentType.JSON)
                        .header("Authorization", token)
                .when()
                        .delete(CART_DELETE_CANCEL)
                ;
    }

}
