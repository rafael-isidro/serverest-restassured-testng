package story;

public class ProductStory {

    public static final String EPIC_PRODUCT = "Produto";

    public static final String PRODUCT_STORY_POST = "Como administrador, desejo realizar o cadastro de um produto para disponibilizá-lo em estoque.";
    public static final String PRODUCT_STORY_GET = "Como usuario logado, desejo listar todos os produtos para verificar suas informações de cadastro.";
    public static final String PRODUCT_STORY_PUT = "Como administrador, desejo editar produto por id para atualizar suas informações de cadastro.";
    public static final String PRODUCT_STORY_DELETE = "Como administrador, desejo excluir produto por id para removê-lo do sistema.";

    //POST
    public static final String CT_POST_001 = "CT-01 Validar cadastro de produto com dados válidos";
    public static final String CT_POST_002 = "CT-02 Tentar cadastro de produto com dados vazios";
    public static final String CT_POST_003 = "CT-03 Tentar cadastro de produto informando preço negativo";
    public static final String CT_POST_004 = "CT-04 Tentar cadastro de produto informando preço igual a zero";

    //GET
    public static final String CT_GET_001 = "CT-01 Validar listagem de produtos com sucesso";
    public static final String CT_GET_002 = "CT-02 Validar busca de produto por ID com sucesso";
    public static final String CT_GET_003 = "CT-03 Tentar buscar de produto por ID não cadastrado";

    //PUT
    public static final String CT_PUT_001 = "CT-01 Validar edição de produto com dados válidos";
    public static final String CT_PUT_002 = "CT-02 Tentar edição de produto com dados vazios";
    public static final String CT_PUT_003 = "CT-03 Tentar edição de produto informando preço negativo";
    public static final String CT_PUT_004 = "CT-04 Tentar edição de produto informando preço igual a zero";

    //DELETE
    public static final String CT_DELETE_001 = "CT-01 Validar exclusão de produto por ID com sucesso";
    public static final String CT_DELETE_002 = "CT-02 Tentar excluir produto por ID não cadastrado";
}
