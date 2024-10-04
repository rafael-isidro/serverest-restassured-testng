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
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static story.ProductStory.*;
import static utils.messages.ProductMessages.PRODUCT_MESSAGES;

@Epic(EPIC_PRODUCT)
@Story(PRODUCT_STORY_DELETE)
public class ProductDeleteTest {

    private static final ProductClient productClient = new ProductClient();
    private static final LoginClient loginClient = new LoginClient();
    private static String productExistingId;
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

    @Test
    @Description(CT_DELETE_001)
    public void testValidarExclusaoProdutoComSucesso() {
        registrarProduto();

        String response = productClient.deleteProduct(productExistingId, token)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                    .path("message");

        Assert.assertEquals(response, PRODUCT_MESSAGES.successDelete());

        excluirUsuario();
    }

    @Test
    @Description(CT_DELETE_002)
    public void testTentarExcluirProdutoPorIDNaoCadastrado() {

        String productId = ProductDataFactory.notRegisteredId();

        String response = productClient.deleteProduct(productId, token)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                    .path("message");

        Assert.assertEquals(response, PRODUCT_MESSAGES.productNotDeleted());
    }

    private static void registrarProduto() {
        PostProductRequestModel product = ProductDataFactory.validProduct();

        productExistingId = productClient.registerProduct(product, token)
                .then()
                    .extract()
                    .path("_id");
    }

    private static void excluirUsuario() {
        productClient.deleteProduct(productExistingId, token)
                .then()
                    .statusCode(HttpStatus.SC_OK)
        ;
    }

}
