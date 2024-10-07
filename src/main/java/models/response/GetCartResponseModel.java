package models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCartResponseModel {

    private List<GetCartProductResponseModel> produtos;
    private Integer precoTotal;
    private Integer quantidadeTotal;
    private String idUsuario;
    private String _id;

}
