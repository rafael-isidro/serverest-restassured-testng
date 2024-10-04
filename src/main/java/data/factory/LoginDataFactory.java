package data.factory;

import models.request.AuthRequestModel;
import net.datafaker.Faker;
import utils.Manipulation;

public class LoginDataFactory {

    private static final String USER_EMAIL_PROP = "USER_EMAIL";
    private static final String USER_PSW_PROP = "USER_PSW";
    private static final Faker faker = new Faker();

    public static AuthRequestModel validLogin() {
        return new AuthRequestModel(
                Manipulation.getProp().getProperty(USER_EMAIL_PROP),
                Manipulation.getProp().getProperty(USER_PSW_PROP)
        );
    }

    public static AuthRequestModel invalidCredentialsLogin() {
        return new AuthRequestModel(
                faker.internet().emailAddress(),
                faker.internet().password()
        );
    }

    public static AuthRequestModel invalidEmailLogin() {
        return new AuthRequestModel(
                faker.internet().emailAddress(),
                Manipulation.getProp().getProperty(USER_PSW_PROP)
        );
    }

    public static AuthRequestModel invalidFormatEmailLogin() {
        return new AuthRequestModel(
                faker.name().username(),
                Manipulation.getProp().getProperty(USER_PSW_PROP)
        );
    }

    public static AuthRequestModel invalidPasswordLogin() {
        return new AuthRequestModel(
                Manipulation.getProp().getProperty(USER_EMAIL_PROP),
                faker.internet().password()
        );
    }

    public static AuthRequestModel emptyEmailLogin() {
        return new AuthRequestModel("", Manipulation.getProp().getProperty(USER_PSW_PROP));
    }

    public static AuthRequestModel emptyPasswordLogin() {
        return new AuthRequestModel(Manipulation.getProp().getProperty(USER_EMAIL_PROP), "");
    }

}
