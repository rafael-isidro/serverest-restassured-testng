package data.factory;

import models.request.PostCartProductRequestModel;
import models.request.PostCartRequestModel;
import net.datafaker.Faker;

import java.util.Collections;

public class CartDataFactory {

    private static final Faker faker = new Faker();

    public static PostCartProductRequestModel validProductCart(String productId) {
        return new PostCartProductRequestModel(
                productId,
                faker.number().numberBetween(2, 5)
        );
    }

    public static PostCartRequestModel validCart(String productId) {
        PostCartRequestModel cart = new PostCartRequestModel();
        PostCartProductRequestModel productCart = CartDataFactory.validProductCart(productId);

        cart.setProdutos(Collections.singletonList(productCart));

        return cart;
    }

    public static PostCartRequestModel negativeQuantityProductCart(String productId) {
        PostCartRequestModel cart = CartDataFactory.validCart(productId);

        cart.getProdutos().get(0).setQuantidade(faker.number().negative());

        return cart;
    }

    public static String notRegisteredId() {
        return faker.lorem().characters(10, true);
    }

}
