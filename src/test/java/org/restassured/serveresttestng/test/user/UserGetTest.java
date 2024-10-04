package org.restassured.serveresttestng.test.user;

import client.UserClient;
import data.factory.UserDataFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import models.response.GetAllUsersResponseModel;
import models.response.GetUserResponseModel;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static story.UserStory.*;
import static utils.messages.UserMessages.USER_MESSAGES;

@Epic(EPIC_USER)
@Story(USER_STORY_GET)
public class UserGetTest {

    private static final UserClient userClient = new UserClient();
    private static final SoftAssert softAssert = new SoftAssert();

    @Test(priority = 1)
    @Description(CT_GET_001)
    public void testValidarListarUsuariosComSucesso() {
        GetAllUsersResponseModel users = userClient.getAllUsers()
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                    .as(GetAllUsersResponseModel.class);

        softAssert.assertTrue(users.getQuantidade() > 0);
        softAssert.assertFalse(users.getUsuarios().isEmpty());

        softAssert.assertAll();
    }

    @Test(priority = 2)
    @Description(CT_GET_002)
    public void testValidarBuscarUsuarioPorIDComSucesso() {

        String userId = UserDataFactory.validUserId();

        GetUserResponseModel user = userClient.getUserById(userId)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                    .as(GetUserResponseModel.class);

        Assert.assertEquals(user.get_id(), userId);
    }

    @Test(priority = 3)
    @Description(CT_GET_003)
    public void testTentarBuscarUsuarioPorIDNaoCadastrado() {

        String userId = UserDataFactory.notRegisteredId();

        String response = userClient.getUserById(userId)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract()
                    .path("message");

        Assert.assertEquals(response, USER_MESSAGES.userNotFound());
    }
}
