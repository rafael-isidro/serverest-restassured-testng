package data.factory;

import models.request.PostProductRequestModel;
import net.datafaker.Faker;

public class ProductDataFactory {

    private static final Faker faker = new Faker();

    public static PostProductRequestModel validProduct() {
        return new PostProductRequestModel(
                faker.commerce().productName(),
                faker.number().numberBetween(2, 1000),
                faker.commerce().department(),
                faker.number().numberBetween(15, 100)
        );
    }

    public static PostProductRequestModel emptyNameDescriptionProduct() {
        return new PostProductRequestModel(
                "",
                faker.number().numberBetween(2, 1000),
                "",
                faker.number().numberBetween(15, 100)
        );
    }

    public static PostProductRequestModel negativePriceProduct() {
        return new PostProductRequestModel(
                faker.commerce().productName(),
                faker.number().negative(),
                faker.commerce().department(),
                faker.number().numberBetween(15, 100)
        );
    }

    public static PostProductRequestModel zeroPriceProduct() {
        return new PostProductRequestModel(
                faker.commerce().productName(),
                0,
                faker.commerce().department(),
                faker.number().numberBetween(15, 100)
        );
    }

    public static String notRegisteredId() {
        return faker.lorem().characters(10, true);
    }

}