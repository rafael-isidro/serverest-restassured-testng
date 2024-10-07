package story;

public class CartStory {

    public static final String EPIC_CART = "Carrinho";

    public static final String CART_STORY_POST = "Como usuário logado, desejo realizar o cadastro de um carrinho para realizar a compra.";
    public static final String CART_STORY_GET = "Como usuario logado, desejo listar todos os carrinhos para verificar suas informações de cadastro.";
    public static final String CART_STORY_DELETE = "Como usuário, desejo excluir meu carrinho após concluir ou cancelar minha compra.";

    //POST
    public static final String CT_POST_001 = "CT-01 Validar cadastro de carrinho com sucesso";
    public static final String CT_POST_002 = "CT-02 Tentar cadastro de carrinho com id de produto inexistente";
    public static final String CT_POST_003 = "CT-03 Tentar cadastro de carrinho com quantidade de produto negativa";

    //GET
    public static final String CT_GET_001 = "CT-01 Validar listagem de carrinhos com sucesso";
    public static final String CT_GET_002 = "CT-02 Validar busca de carrinho por ID com sucesso";
    public static final String CT_GET_003 = "CT-03 Tentar buscar carrinho por ID não cadastrado";

    //DELETE BUY
    public static final String CT_DELETE_001 = "CT-01 Validar exclusão de carrinho após compra com sucesso";
    public static final String CT_DELETE_002 = "CT-02 Tentar exclusão de carrinho após compra sem possuir carrinho cadastrado";

    //DELETE CANCEL
    public static final String CT_DELETE_003 = "CT-03 Validar exclusão de carrinho com cancelamento de compra com sucesso";
    public static final String CT_DELETE_004 = "CT-04 Tentar exclusão de carrinho com cancelamento de compra sem possuir carrinho cadastradoo";
}
