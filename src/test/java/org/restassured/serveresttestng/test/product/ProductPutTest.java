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
import models.response.PostProductResponseModel;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static story.ProductStory.*;
import static utils.messages.ProductMessages.PRODUCT_MESSAGES;

@Epic(EPIC_PRODUCT)
@Story(PRODUCT_STORY_PUT)
public class ProductPutTest {

    private static final ProductClient productClient = new ProductClient();
    private static final LoginClient loginClient = new LoginClient();
    private static final SoftAssert softAssert = new SoftAssert();
    private static String token;
    private static String productExistingId;

    @BeforeClass
    static void setup() {
        AuthRequestModel loginCredentials = LoginDataFactory.validLogin();

        token = loginClient.login(loginCredentials)
                    .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .path("authorization")
                ;

        PostProductRequestModel product = ProductDataFactory.validProduct();

        productExistingId = productClient.registerProduct(product, token)
                .then()
                    .extract()
                    .path("_id");
    }

    @AfterClass
    static void tearDown() {
        productClient.deleteProduct(productExistingId, token);
    }

    @Test
    @Description(CT_PUT_001)
    public void testEditarProducoComSucesso() {

        PostProductRequestModel product = ProductDataFactory.validProduct();

        PostProductResponseModel response = productClient.updateProduct(productExistingId, product, token)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                    .as(PostProductResponseModel.class);

        Assert.assertEquals(response.getMessage(), PRODUCT_MESSAGES.successUpdate());

    }

    @Test
    @Description(CT_PUT_002)
    public void testTentarEditarProdutoComNomeDescriptionVazios() {

        PostProductRequestModel product = ProductDataFactory.emptyNameDescriptionProduct();

        PostProductRequestModel response = productClient.updateProduct(productExistingId, product, token)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract()
                    .as(PostProductRequestModel.class);

        softAssert.assertEquals(response.getNome(), PRODUCT_MESSAGES.emptyName());
        softAssert.assertEquals(response.getDescricao(), PRODUCT_MESSAGES.emptyDescription());

        softAssert.assertAll();
    }

    @Test
    @Description(CT_PUT_003)
    public void testTentarEditarProdutoComPrecoNegativo() {

        PostProductRequestModel product = ProductDataFactory.negativePriceProduct();

        String response = productClient.updateProduct(productExistingId, product, token)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract()
                    .path("preco");

        Assert.assertEquals(response, PRODUCT_MESSAGES.zeroNegativePrice());

    }

    @Test
    @Description(CT_PUT_004)
    public void testTentarEditarProdutoComPrecoIgualAZero() {

        PostProductRequestModel product = ProductDataFactory.zeroPriceProduct();

        String response = productClient.updateProduct(productExistingId, product, token)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract()
                    .path("preco");

        Assert.assertEquals(response, PRODUCT_MESSAGES.zeroNegativePrice());

    }

}
