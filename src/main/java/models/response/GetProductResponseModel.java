package models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetProductResponseModel {

    private String nome;
    private Integer preco;
    private String descricao;
    private Integer quantidade;
    private String _id;

}
