package models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCartsResponseModel {

    private Integer quantidade;
    private List<GetCartResponseModel> carrinhos;
}
