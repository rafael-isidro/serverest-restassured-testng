package data.factory;

import models.request.PostUserRequestModel;
import net.datafaker.Faker;
import utils.Manipulation;

public class UserDataFactory {

    private static final Faker faker = new Faker();
    private static final String USER_EMAIL_PROP = "USER_EMAIL";
    private static final String USER_ID_PROP = "USER_ID";

    public static PostUserRequestModel validAdminUser() {
        return new PostUserRequestModel(
                faker.name().username(),
                faker.internet().emailAddress(),
                faker.internet().password(),
                "true"
        );
    }

    public static String validUserId() {
        return Manipulation.getProp().getProperty(USER_ID_PROP);
    }

    public static String notRegisteredId() {
        return faker.lorem().characters(10, true);
    }


    public static PostUserRequestModel invalidFormatEmailUser() {
        return new PostUserRequestModel(
                faker.name().username(),
                faker.name().username(),
                faker.internet().password(),
                String.valueOf(faker.random().nextBoolean())
        );
    }

    public static PostUserRequestModel emptyDataUser() {
        return new PostUserRequestModel(
                "",
                "",
                "",
                ""
        );
    }

    public static PostUserRequestModel existingEmailUser() {
        return new PostUserRequestModel(
                faker.name().username(),
                Manipulation.getProp().getProperty(USER_EMAIL_PROP),
                faker.internet().password(),
                String.valueOf(faker.random().nextBoolean())
        );
    }
}
