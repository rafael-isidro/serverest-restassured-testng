package story;

public class UserStory {

    public static final String EPIC_USER = "Usuario";

    public static final String USER_STORY_POST = "Como usuário visitante, desejo realizar o cadastro para estar autenticado a acessar o sistema.";
    public static final String USER_STORY_GET = "Como administrador, desejo listar todos os usuários para verificar suas informações de cadastro.";
    public static final String USER_STORY_PUT = "Como administrador, desejo editar usuário por id para atualizar suas informações de cadastro.";
    public static final String USER_STORY_DELETE = "Como administrador, desejo excluir usuário por id para descadastrar um usuário do sistema.";

    //POST
    public static final String CT_REGISTER_001 = "CT-01 Validar registro com dados válidos";
    public static final String CT_REGISTER_002 = "CT-02 Tentar registro com dados vazios";
    public static final String CT_REGISTER_003 = "CT-03 Tentar registro com email com formato inválido";
    public static final String CT_REGISTER_004 = "CT-04 Tentar registro com email já existente";

    //GET
    public static final String CT_GET_001 = "CT-01 Validar listagem de usuários com sucesso";
    public static final String CT_GET_002 = "CT-02 Validar busca de usuário por ID com sucesso";
    public static final String CT_GET_003 = "CT-03 Tentar buscar usuário por ID não cadastrado";

    //PUT
    public static final String CT_PUT_001 = "CT-01 Validar edição de usuário com sucesso";
    public static final String CT_PUT_002 = "CT-02 Tentar editar usuário informando dados vazios";
    public static final String CT_PUT_003 = "CT-03 Tentar editar usuário informando email com formato inválido";
    public static final String CT_PUT_004 = "CT-04 Tentar editar usuário informando email já existente de outro usuário";

    //DELETE
    public static final String CT_DELETE_001 = "CT-01 Validar exclusão de usuário por ID com sucesso";
    public static final String CT_DELETE_002 = "CT-02 Tentar excluir usuário por ID não cadastrado";
}
