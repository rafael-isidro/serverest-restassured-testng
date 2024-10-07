package org.restassured.serveresttestng.test.cart;

import client.CartClient;
import client.LoginClient;
import client.ProductClient;
import data.factory.CartDataFactory;
import data.factory.LoginDataFactory;
import data.factory.ProductDataFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import models.request.AuthRequestModel;
import models.request.PostCartProductRequestModel;
import models.request.PostCartRequestModel;
import models.request.PostProductRequestModel;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;

import static story.CartStory.*;
import static utils.messages.CartMessages.CART_MESSAGES;

@Epic(EPIC_CART)
@Story(CART_STORY_DELETE)
@Test()
public class CartDeleteTest {

    private static final LoginClient loginClient = new LoginClient();
    private static final CartClient cartClient = new CartClient();
    private static final ProductClient productClient = new ProductClient();
    private static String token;
    private static String productId;
    private static String cartId;

    @BeforeMethod
    public void setup() {
        token = loginGetToken();
        productId = registerProductAndGetId(token);
        cartId = createCartAndGetId(token, productId);
    }

    @AfterMethod
    public void teardown() {
        if (cartId != null) deleteCartAndProduct(token, productId);
    }

    @Description(CT_DELETE_001)
    public void testValidarExclusaoCarrinhoAposCompraComSucesso() {
        String response = cartClient.deleteCartBuy(token)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                    .path("message");

        Assert.assertEquals(response, CART_MESSAGES.successDeleteBuy());

    }

    @Description(CT_DELETE_002)
    public void testTentarExclusaoCarrinhoAposCompraSemCarrinho() {

        if (cartId != null) deleteCartAndProduct(token, productId);

        String response = cartClient.deleteCartBuy(token)
                .then()
                    .extract()
                    .path("message");

        Assert.assertEquals(response, CART_MESSAGES.userCartNotFound());

    }

    @Description(CT_DELETE_003)
    public void testValidarExclusaoCarrinhoCancelamentoComSucesso() {
        String response = cartClient.deleteCartCancel(token)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                    .path("message");

        Assert.assertEquals(response, CART_MESSAGES.successDeleteCancel());
    }

    @Description(CT_DELETE_004)
    public void testTentarExclusaoCarrinhoCancelamentoSemCarrinho() {

        if (cartId != null) deleteCartAndProduct(token, productId);

        String response = cartClient.deleteCartCancel(token)
                .then()
                    .extract()
                    .path("message");

        Assert.assertEquals(response, CART_MESSAGES.userCartNotFound());

    }

    private String loginGetToken() {
        AuthRequestModel loginCredentials = LoginDataFactory.validLogin();
        return loginClient.login(loginCredentials)
                .then()
                    .extract()
                    .path("authorization");
    }

    private String registerProductAndGetId(String token) {
        PostProductRequestModel product = ProductDataFactory.validProduct();
        return productClient.registerProduct(product, token)
                .then()
                    .extract()
                    .path("_id");
    }

    private String createCartAndGetId(String token, String productId) {
        PostCartProductRequestModel productCart = CartDataFactory.validProductCart(productId);
        PostCartRequestModel cart = new PostCartRequestModel();
        cart.setProdutos(Collections.singletonList(productCart));
        return cartClient.postCart(cart, token)
                .then()
                    .extract()
                    .path("_id");
    }

    private void deleteCartAndProduct(String token, String productId) {
        cartClient.deleteCartCancel(token);
        productClient.deleteProduct(productId, token);
    }
}
