package utils.messages;

public record LoginMessages(
        String successLogin,
        String invalidFormatEmail,
        String invalidEmailPassword,
        String emptyEmail,
        String emptyPassword
) {
    public static final LoginMessages LOGIN_MESSAGES = new LoginMessages(
            "Login realizado com sucesso",
            "email deve ser um email válido",
            "Email e/ou senha inválidos",
            "email não pode ficar em branco",
            "password não pode ficar em branco"
    );
}