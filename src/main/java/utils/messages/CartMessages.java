package utils.messages;

public record CartMessages(
        String successRegister,
        String cartNotFound,
        String userCartNotFound,
        String negativeQuantity,
        String successDeleteBuy,
        String successDeleteCancel,
        String cartNotDeleted
) {
    public static final CartMessages CART_MESSAGES = new CartMessages(
            "Cadastro realizado com sucesso",
            "Carrinho não encontrado",
            "Não foi encontrado carrinho para esse usuário",
            "produtos[0].quantidade deve ser um número positivo",
            "Registro excluído com sucesso",
            "Registro excluído com sucesso. Estoque dos produtos reabastecido",
            "Nenhum registro excluído"
    );
}