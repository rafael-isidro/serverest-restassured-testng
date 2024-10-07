package models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCartProductResponseModel {

    private String idProduto;
    private Integer quantidade;
    private Integer precoUnitario;

}
