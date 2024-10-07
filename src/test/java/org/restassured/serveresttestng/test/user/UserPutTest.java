package org.restassured.serveresttestng.test.user;

import client.UserClient;
import data.factory.UserDataFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import models.request.PostUserRequestModel;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static story.UserStory.*;
import static utils.messages.UserMessages.USER_MESSAGES;

@Epic(EPIC_USER)
@Story(USER_STORY_PUT)
@Test
public class UserPutTest {

    private static final UserClient userClient = new UserClient();
    private static final SoftAssert softAssert = new SoftAssert();
    private static String userExistingId;

    @BeforeClass
    public static void setUp() {

        PostUserRequestModel user = UserDataFactory.validAdminUser();

        userExistingId = userClient.registerUser(user)
            .then()
                .extract()
                .path("_id");

    }

    @AfterClass
    public static void tearDown() {

        userClient.deleteUser(userExistingId)
            .then()
                .statusCode(HttpStatus.SC_OK);

    }

    @Description(CT_PUT_001)
    public void testEditarUsuarioComSucesso() {

        PostUserRequestModel user = UserDataFactory.validAdminUser();

        String response = userClient.updateUser(userExistingId, user)
                .then()
                    .extract()
                    .path("message");

        Assert.assertEquals(response, USER_MESSAGES.successUpdate());

    }

    @Description(CT_PUT_002)
    public void testTentarEditarUsuarioComFormatoEmailInvalido() {

        PostUserRequestModel user = UserDataFactory.invalidFormatEmailUser();

        String response =
                userClient.updateUser(userExistingId, user)
                    .then()
                        .statusCode(HttpStatus.SC_BAD_REQUEST)
                        .extract()
                        .path("email")
                ;

        Assert.assertEquals(response, USER_MESSAGES.invalidEmail());

    }

    @Description(CT_PUT_003)
    public void testTentarEditarUsuarioComDadosVazios() {

        PostUserRequestModel user = UserDataFactory.emptyDataUser();

        PostUserRequestModel response =
                userClient.updateUser(userExistingId, user)
                    .then()
                        .statusCode(HttpStatus.SC_BAD_REQUEST)
                        .extract()
                        .as(PostUserRequestModel.class);
                ;

        softAssert.assertEquals(response.getNome(), USER_MESSAGES.emptyName());
        softAssert.assertEquals(response.getEmail(), USER_MESSAGES.emptyEmail());
        softAssert.assertEquals(response.getPassword(), USER_MESSAGES.emptyPassword());
        softAssert.assertEquals(response.getAdministrador(), USER_MESSAGES.invalidAdm());

        softAssert.assertAll();
    }

    @Description(CT_PUT_004)
    public void testTentarEditarUsuarioComEmailJaExistente() {

        PostUserRequestModel user = UserDataFactory.existingEmailUser();

        String response =
                userClient.updateUser(userExistingId, user)
                    .then()
                        .statusCode(HttpStatus.SC_BAD_REQUEST)
                        .extract()
                        .path("message");
        ;

        Assert.assertEquals(response, USER_MESSAGES.existingEmail());
    }
}
