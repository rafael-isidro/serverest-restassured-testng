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
import models.response.GetAllCartsResponseModel;
import models.response.GetCartResponseModel;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Collections;

import static story.CartStory.*;
import static utils.messages.CartMessages.CART_MESSAGES;

@Epic(EPIC_CART)
@Story(CART_STORY_GET)
@Test()
public class CartGetTest {

    private static final LoginClient loginClient = new LoginClient();
    private static final CartClient cartClient = new CartClient();
    private static final ProductClient productClient = new ProductClient();
    private static final SoftAssert softAssert = new SoftAssert();
    private static String token;
    private static String productId;
    private static String cartId;

    @BeforeClass
    public void setup() {
        token = loginGetToken();
        productId = registerProductAndGetId(token);
        cartId = createCartAndGetId(token, productId);
    }

    @AfterClass
    public void teardown() {
        deleteCartAndProduct(token, productId);
    }

    @Description(CT_GET_001)
    public void testValidarListarCarrinhosComSucesso() {
        GetAllCartsResponseModel response = cartClient.getAllCarts()
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                    .as(GetAllCartsResponseModel.class);

        softAssert.assertTrue(response.getQuantidade() > 0, "A quantidade de carrinhos deve ser maior que 0.");
        softAssert.assertFalse(response.getCarrinhos().isEmpty(), "A lista de carrinhos não deve estar vazia.");
        softAssert.assertAll();
    }

    @Description(CT_GET_002)
    public void testValidarBuscarCarrinhoPorIdComSucesso() {
        GetCartResponseModel response = cartClient.getCartById(cartId)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                    .as(GetCartResponseModel.class);

        softAssert.assertEquals(response.get_id(), cartId, "O ID do carrinho deve corresponder.");
        softAssert.assertFalse(response.getProdutos().isEmpty(), "A lista de produtos não deve estar vazia.");
        softAssert.assertTrue(response.getQuantidadeTotal() > 0, "A quantidade total de produtos deve ser maior que 0.");
        softAssert.assertAll();
    }

    @Description(CT_GET_003)
    public void testTentarBuscarCarrinhoPorIdNaoCadastrado() {
        String notRegisteredId = CartDataFactory.notRegisteredId();

        String response = cartClient.getCartById(notRegisteredId)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract()
                    .path("message");

        Assert.assertEquals(response, CART_MESSAGES.cartNotFound());

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
