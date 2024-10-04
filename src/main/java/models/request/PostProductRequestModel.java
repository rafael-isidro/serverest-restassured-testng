package models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostProductRequestModel {

    private String nome;
    private Integer preco;
    private String descricao;
    private Integer quantidade;

}