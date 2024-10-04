package utils.messages;

public record UserMessages(
        String successRegister,
        String invalidEmail,
        String emptyName,
        String emptyEmail,
        String emptyPassword,
        String invalidAdm,
        String existingEmail,
        String userNotFound,
        String successUpdate,
        String successDelete,
        String userNotDeleted
) {
    public static final UserMessages USER_MESSAGES = new UserMessages(
            "Cadastro realizado com sucesso",
            "email deve ser um email válido",
            "nome não pode ficar em branco",
            "email não pode ficar em branco",
            "password não pode ficar em branco",
            "administrador deve ser 'true' ou 'false'",
            "Este email já está sendo usado",
            "Usuário não encontrado",
            "Registro alterado com sucesso",
            "Registro excluído com sucesso",
            "Nenhum registro excluído"
    );
}