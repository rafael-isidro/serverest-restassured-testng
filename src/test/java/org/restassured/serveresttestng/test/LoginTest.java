package org.restassured.serveresttestng.test;

import client.LoginClient;
import data.factory.LoginDataFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import models.request.AuthRequestModel;
import models.response.AuthResponseModel;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static story.LoginStory.*;
import static utils.messages.LoginMessages.LOGIN_MESSAGES;

@Epic(EPIC_LOGIN)
@Story(USER_STORY_LOGIN_POST)
public class LoginTest {

        private static final LoginClient loginClient = new LoginClient();
        private static final SoftAssert softAssert = new SoftAssert();

        @Test(priority = 1)
        @Description(CT_LOGIN_001)
        public void testRealizarLoginComSucesso() {

            AuthRequestModel loginCredentials = LoginDataFactory.validLogin();

            AuthResponseModel response =
                    loginClient.login(loginCredentials)
                        .then()
                            .statusCode(HttpStatus.SC_OK)
                            .extract()
                            .as(AuthResponseModel.class)
                    ;

            softAssert.assertNotNull(response.getAuthorization());
            softAssert.assertEquals(response.getMessage(), LOGIN_MESSAGES.successLogin());

            softAssert.assertAll();
        }

        @Test(priority = 2)
        @Description(CT_LOGIN_002)
        public void testTentarLoginDadosInvalidos() {

            AuthRequestModel loginCredentials = LoginDataFactory.invalidCredentialsLogin();

            String response =
                    loginClient.login(loginCredentials)
                        .then()
                            .statusCode(HttpStatus.SC_UNAUTHORIZED)
                            .extract()
                            .path("message")
                    ;

            Assert.assertEquals(response, LOGIN_MESSAGES.invalidEmailPassword());
        }

        @Test(priority = 3)
        @Description(CT_LOGIN_003)
        public void testTentarLoginEmailInvalido() {

            AuthRequestModel loginCredentials = LoginDataFactory.invalidEmailLogin();

            String response =
                    loginClient.login(loginCredentials)
                        .then()
                            .statusCode(HttpStatus.SC_UNAUTHORIZED)
                            .extract()
                            .path("message")
                    ;

            Assert.assertEquals(response, LOGIN_MESSAGES.invalidEmailPassword());
        }

        @Test(priority = 4)
        @Description(CT_LOGIN_004)
        public void testTentarLoginSenhaInvalida() {

            AuthRequestModel loginCredentials = LoginDataFactory.invalidPasswordLogin();

            String response =
                    loginClient.login(loginCredentials)
                        .then()
                            .statusCode(HttpStatus.SC_UNAUTHORIZED)
                            .extract()
                            .path("message")
                    ;

            Assert.assertEquals(response, LOGIN_MESSAGES.invalidEmailPassword());
        }

        @Test(priority = 5)
        @Description(CT_LOGIN_005)
        public void testTentarLoginEmailVazio() {

            AuthRequestModel loginCredentials = LoginDataFactory.emptyEmailLogin();

            String response =
                    loginClient.login(loginCredentials)
                        .then()
                            .statusCode(HttpStatus.SC_BAD_REQUEST)
                            .extract()
                            .path("email")
                    ;

            Assert.assertEquals(response, LOGIN_MESSAGES.emptyEmail());
        }

        @Test(priority = 6)
        @Description(CT_LOGIN_006)
        public void testTentarLoginEmailFormatoInvalido() {

            AuthRequestModel loginCredentials = LoginDataFactory.invalidFormatEmailLogin();

            String response =
                    loginClient.login(loginCredentials)
                        .then()
                            .statusCode(HttpStatus.SC_BAD_REQUEST)
                            .extract()
                            .path("email")
                    ;

            Assert.assertEquals(response, LOGIN_MESSAGES.invalidFormatEmail());
        }

        @Test(priority = 7)
        @Description(CT_LOGIN_007)
        public void testTentarLoginSenhaVazia() {

            AuthRequestModel loginCredentials = LoginDataFactory.emptyPasswordLogin();

            String response =
                    loginClient.login(loginCredentials)
                        .then()
                            .statusCode(HttpStatus.SC_BAD_REQUEST)
                            .extract()
                            .path("password")
                    ;

            Assert.assertEquals(response, LOGIN_MESSAGES.emptyPassword());
        }

}
