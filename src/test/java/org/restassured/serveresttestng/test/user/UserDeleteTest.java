package org.restassured.serveresttestng.test.user;

import client.UserClient;
import data.factory.UserDataFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import models.request.PostUserRequestModel;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static story.UserStory.*;
import static utils.messages.UserMessages.USER_MESSAGES;

@Epic(EPIC_USER)
@Story(USER_STORY_DELETE)
@Test
public class UserDeleteTest {

    private static final UserClient userClient = new UserClient();
    private static String userExistingId;

    @Description(CT_DELETE_001)
    public void testValidarExclusaoUsuarioComSucesso() {
        registrarUsuario();

        String response = userClient.deleteUser(userExistingId)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                    .path("message");

        Assert.assertEquals(response, USER_MESSAGES.successDelete());

        excluirUsuario();
    }

    @Description(CT_DELETE_002)
    public void testTentarExcluirUsuarioPorIDNaoCadastrado() {

        String userId = UserDataFactory.notRegisteredId();

        String response = userClient.deleteUser(userId)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                    .path("message");

        Assert.assertEquals(response, USER_MESSAGES.userNotDeleted());
    }

    private static void registrarUsuario() {
        PostUserRequestModel user = UserDataFactory.validUser();

        userExistingId = userClient.registerUser(user)
                .then()
                    .extract()
                    .path("_id");
    }

    private static void excluirUsuario() {
        userClient.deleteUser(userExistingId)
                .then()
                    .statusCode(HttpStatus.SC_OK)
        ;
    }

}
