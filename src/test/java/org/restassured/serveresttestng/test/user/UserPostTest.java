package org.restassured.serveresttestng.test.user;

import client.UserClient;
import data.factory.UserDataFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import models.request.PostUserRequestModel;
import models.response.PostUserResponseModel;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static story.UserStory.*;
import static utils.messages.UserMessages.USER_MESSAGES;

@Epic(EPIC_USER)
@Story(USER_STORY_POST)
public class UserPostTest {

    private static final UserClient userClient = new UserClient();
    private static final SoftAssert softAssert = new SoftAssert();

    @Test
    @Description(CT_REGISTER_001)
    public void testRealizarRegistroComSucesso() {

        PostUserRequestModel user = UserDataFactory.validUser();

        PostUserResponseModel response = userClient.registerUser(user)
                .then()
                    .extract()
                    .as(PostUserResponseModel.class);

        Assert.assertEquals(response.getMessage(), USER_MESSAGES.successRegister());

    }

    @Test
    @Description(CT_REGISTER_002)
    public void testTentarRegistroComFormatoEmailInvalido() {

        PostUserRequestModel user = UserDataFactory.invalidFormatEmailUser();

        String response =
                userClient.registerUser(user)
                    .then()
                        .statusCode(HttpStatus.SC_BAD_REQUEST)
                        .extract()
                        .path("email")
                ;

        Assert.assertEquals(response, USER_MESSAGES.invalidEmail());

    }

    @Test
    @Description(CT_REGISTER_003)
    public void testTentarRegistroComDadosVazios() {

        PostUserRequestModel user = UserDataFactory.emptyDataUser();

        PostUserRequestModel response =
                userClient.registerUser(user)
                    .then()
                        .statusCode(HttpStatus.SC_BAD_REQUEST)
                        .extract()
                        .as(PostUserRequestModel.class)
                ;

        softAssert.assertEquals(response.getNome(), USER_MESSAGES.emptyName());
        softAssert.assertEquals(response.getEmail(), USER_MESSAGES.emptyEmail());
        softAssert.assertEquals(response.getPassword(), USER_MESSAGES.emptyPassword());
        softAssert.assertEquals(response.getAdministrador(), USER_MESSAGES.invalidAdm());

        softAssert.assertAll();
    }

    @Test
    @Description(CT_REGISTER_004)
    public void testTentarRegistroComEmailJaExistente() {

        PostUserRequestModel user = UserDataFactory.existingEmailUser();

        String response =
                userClient.registerUser(user)
                    .then()
                        .statusCode(HttpStatus.SC_BAD_REQUEST)
                        .extract()
                        .path("message");
        ;

        Assert.assertEquals(response, USER_MESSAGES.existingEmail());
    }
}
