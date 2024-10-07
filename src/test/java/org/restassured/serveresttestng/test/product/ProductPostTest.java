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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static story.ProductStory.*;
import static utils.messages.ProductMessages.PRODUCT_MESSAGES;

@Epic(EPIC_PRODUCT)
@Story(PRODUCT_STORY_POST)
@Test
public class ProductPostTest {

    private static final ProductClient productClient = new ProductClient();
    private static final LoginClient loginClient = new LoginClient();
    private static final SoftAssert softAssert = new SoftAssert();
    private static String token;

    @BeforeClass
    static void setup() {
        AuthRequestModel loginCredentials = LoginDataFactory.validLogin();

        token = loginClient.login(loginCredentials)
                    .then()
                        .statusCode(HttpStatus.SC_OK)
                        .extract()
                        .path("authorization")
                ;
    }
    
    @Description(CT_POST_001)
    public void testRealizarRegistroComSucesso() {

        PostProductRequestModel product = ProductDataFactory.validProduct();

        PostProductResponseModel response = productClient.registerProduct(product, token)
                .then()
                    .statusCode(HttpStatus.SC_CREATED)
                    .extract()
                    .as(PostProductResponseModel.class);

        Assert.assertEquals(response.getMessage(), PRODUCT_MESSAGES.successRegister());

        productClient.deleteProduct(response.get_id(), token);

    }
    
    @Description(CT_POST_002)
    public void testTentarRegistroProdutoComNomeDescriptionVazios() {

        PostProductRequestModel product = ProductDataFactory.emptyNameDescriptionProduct();

        PostProductRequestModel response = productClient.registerProduct(product, token)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract()
                    .as(PostProductRequestModel.class);

        softAssert.assertEquals(PRODUCT_MESSAGES.emptyName(), response.getNome());
        softAssert.assertEquals(PRODUCT_MESSAGES.emptyDescription(), response.getDescricao());

        softAssert.assertAll();
    }

    @Description(CT_POST_003)
    public void testTentarRegistroProdutoComPrecoNegativo() {

        PostProductRequestModel product = ProductDataFactory.negativePriceProduct();

        String response = productClient.registerProduct(product, token)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract()
                    .path("preco");

        Assert.assertEquals(response, PRODUCT_MESSAGES.zeroNegativePrice());

    }

    @Description(CT_POST_004)
    public void testTentarRegistroProdutoComPrecoIgualAZero() {

        PostProductRequestModel product = ProductDataFactory.zeroPriceProduct();

        String response = productClient.registerProduct(product, token)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract()
                    .path("preco");

        Assert.assertEquals(response, PRODUCT_MESSAGES.zeroNegativePrice());

    }
}
