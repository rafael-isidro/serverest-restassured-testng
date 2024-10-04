package models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUserRequestModel {

    private String nome;
    private String email;
    private String password;
    private String administrador;

}