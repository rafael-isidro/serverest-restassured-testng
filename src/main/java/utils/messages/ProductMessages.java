package utils.messages;

public record ProductMessages(
        String successRegister,
        String productNotFound,
        String emptyName,
        String emptyDescription,
        String zeroNegativePrice,
        String successUpdate,
        String successDelete,
        String productNotDeleted
) {
    public static final ProductMessages PRODUCT_MESSAGES = new ProductMessages(
            "Cadastro realizado com sucesso",
            "Produto não encontrado",
            "nome não pode ficar em branco",
            "descricao não pode ficar em branco",
            "preco deve ser um número positivo",
            "Registro alterado com sucesso",
            "Registro excluído com sucesso",
            "Nenhum registro excluído"

    );
}