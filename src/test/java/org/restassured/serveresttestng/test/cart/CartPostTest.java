package org.restassured.serveresttestng.test.cart;

import client.CartClient;
import client.LoginClient;
import client.ProductClient;
import client.UserClient;
import data.factory.CartDataFactory;
import data.factory.ProductDataFactory;
import data.factory.UserDataFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import models.request.AuthRequestModel;
import models.request.PostCartRequestModel;
import models.request.PostProductRequestModel;
import models.request.PostUserRequestModel;
import models.response.PostCartResponseModel;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static story.CartStory.*;
import static utils.messages.CartMessages.CART_MESSAGES;
import static utils.messages.ProductMessages.PRODUCT_MESSAGES;

@Epic(EPIC_CART)
@Story(CART_STORY_POST)
@Test()
public class CartPostTest {

    private static final LoginClient loginClient = new LoginClient();
    private static final UserClient userClient = new UserClient();
    private static final CartClient cartClient = new CartClient();
    private static final ProductClient productClient = new ProductClient();

    private static String token;
    private static String productId;

    @BeforeClass
    public void setup() {
        PostUserRequestModel newUser = createUser();
        token = loginGetToken(newUser);
        productId = registerProductAndGetId(token);
    }

    @AfterClass
    public void teardown() {
        deleteCartAndProduct(token, productId);
    }

    @Description(CT_POST_001)
    public void testValidarCadastrarCarrinhoComSucesso() {

        PostCartRequestModel createdCart = CartDataFactory.validCart(productId);

        PostCartResponseModel response = cartClient.postCart(createdCart, token)
                .then()
                    .statusCode(HttpStatus.SC_CREATED)
                    .extract()
                    .as(PostCartResponseModel.class);

        Assert.assertEquals(CART_MESSAGES.successRegister(), response.getMessage());

    }

    @Description(CT_POST_002)
    public void testTentarCadastrarCarrinhoIdProdutoInexistente() {

        PostCartRequestModel createdCart = CartDataFactory.validCart(ProductDataFactory.notRegisteredId());

        String response = cartClient.postCart(createdCart, token)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract()
                    .path("message");

        Assert.assertEquals(PRODUCT_MESSAGES.productNotFound(), response);

    }

    @Description(CT_POST_003)
    public void testTentarCadastrarCarrinhoQuantidadeProdutoNegativa() {

        PostCartRequestModel createdCart = CartDataFactory.negativeQuantityProductCart(productId);

        String response = cartClient.postCart(createdCart, token)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract()
                    .path("'produtos[0].quantidade'");

        Assert.assertEquals(CART_MESSAGES.negativeQuantity(), response);

    }

    private String loginGetToken(PostUserRequestModel newUser) {
        AuthRequestModel loginCredentials = new AuthRequestModel(newUser.getEmail(), newUser.getPassword());
        return loginClient.login(loginCredentials)
                .then()
                    .extract()
                    .path("authorization");
    }

    private PostUserRequestModel createUser() {
        PostUserRequestModel newUser = UserDataFactory.validAdminUser();
        userClient.registerUser(newUser);
        return newUser;
    }

    private String registerProductAndGetId(String token) {
        PostProductRequestModel product = ProductDataFactory.validProduct();
        return productClient.registerProduct(product, token)
                .then()
                    .extract()
                    .path("_id");
    }

    private void deleteCartAndProduct(String token, String productId) {
        cartClient.deleteCartCancel(token);
        productClient.deleteProduct(productId, token);
    }
}
