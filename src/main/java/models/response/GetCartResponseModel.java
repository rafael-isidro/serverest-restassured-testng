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
    private Long precoTotal;
    private Long quantidadeTotal;
    private String idUsuario;
    private String _id;

}
