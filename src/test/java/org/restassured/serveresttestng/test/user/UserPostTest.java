package org.restassured.serveresttestng.test.user;

import client.UserClient;
import data.factory.UserDataFactory;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import models.request.PostUserRequestModel;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static story.UserStory.*;
import static utils.messages.UserMessages.USER_MESSAGES;

@Epic(EPIC_USER)
@Story(USER_STORY_POST)
@Test
public class UserPostTest {
    private static final UserClient userClient = new UserClient();

    @DataProvider(name = "userDataProvider")
    public Object[][] userDataProvider() {
        return new Object[][]{
            {CT_REGISTER_001,       UserDataFactory.validAdminUser(),             HttpStatus.SC_CREATED, USER_MESSAGES.successRegister(),     "message"},
            {CT_REGISTER_002,       UserDataFactory.invalidFormatEmailUser(),     HttpStatus.SC_BAD_REQUEST, USER_MESSAGES.invalidEmail(),    "email"},
            {CT_REGISTER_003,       UserDataFactory.emptyNameUser(),              HttpStatus.SC_BAD_REQUEST, USER_MESSAGES.emptyName(),       "nome"},
            {CT_REGISTER_004,       UserDataFactory.existingEmailUser(),          HttpStatus.SC_BAD_REQUEST, USER_MESSAGES.existingEmail(),   "message"}
        };
    }


    @Test(dataProvider = "userDataProvider")
    public void testRegistroUsuario(String description, PostUserRequestModel user, int statusCode, Object message, String path) {

        io.qameta.allure.Allure.description(description);

        Response response = userClient.registerUser(user)
                .then()
                    .statusCode(statusCode)
                    .extract()
                    .response();

            String actualMessage = response.path(path);
            Assert.assertEquals(actualMessage, message);
    }

}
