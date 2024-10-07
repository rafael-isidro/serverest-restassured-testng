package org.restassured.serveresttestng.test.product;

import client.LoginClient;
import client.ProductClient;
import data.factory.LoginDataFactory;
import data.factory.ProductDataFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import models.request.AuthRequestModel;
import models.request.PostProductRequestModel;
import models.response.GetAllProductsResponseModel;
import models.response.GetProductResponseModel;
import models.response.PostProductResponseModel;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static story.ProductStory.*;
import static utils.messages.ProductMessages.PRODUCT_MESSAGES;

@Epic(EPIC_PRODUCT)
@Story(PRODUCT_STORY_GET)
@Test
public class ProductGetTest {

    private static final ProductClient productClient = new ProductClient();
    private static final LoginClient loginClient = new LoginClient();
    private static final SoftAssert softAssert = new SoftAssert();
    private static String token;

    @BeforeClass
    public static void setup() {

        AuthRequestModel loginCredentials = LoginDataFactory.validLogin();

        token = loginClient.login(loginCredentials)
                    .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .path("authorization")
        ;

    }

    @Description(CT_GET_001)
    public void testValidarListarProdutosComSucesso() {

        GetAllProductsResponseModel products = productClient.getAllProducts()
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                    .as(GetAllProductsResponseModel.class);

        softAssert.assertTrue(products.getQuantidade() > 0);
        softAssert.assertFalse(products.getProdutos().isEmpty());
    }

    @Description(CT_GET_002)
    public void testValidarBuscarProdutoPorIdComSucesso() {

        PostProductResponseModel productRegistered = registerProduct();

        GetProductResponseModel response = productClient.getProductById(productRegistered.get_id())
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                    .as(GetProductResponseModel.class);

        Assert.assertEquals(response.get_id(), productRegistered.get_id());
    }

    @Description(CT_GET_003)
    public void testTentarBuscarUsuarioPorIdNaoCadastrado() {

        String productId = ProductDataFactory.notRegisteredId();

        String response = productClient.getProductById(productId)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract()
                    .path("message");

        Assert.assertEquals(response, PRODUCT_MESSAGES.productNotFound());
    }


    private static PostProductResponseModel registerProduct() {

        PostProductRequestModel product = ProductDataFactory.validProduct();

        return productClient.registerProduct(product, token)
                .then()
                    .log().all()
                    .extract()
                    .as(PostProductResponseModel.class);
    }}
