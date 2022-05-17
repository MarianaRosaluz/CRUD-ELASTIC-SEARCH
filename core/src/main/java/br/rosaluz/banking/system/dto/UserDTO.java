package br.rosaluz.banking.system.dto;

import br.rosaluz.banking.system.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long userid;
    private  String name;
    private String document;
    private String motherName;
    private  String  login;
    private String  password;


    public User convertToUser(){
        return  User.builder()
                .userid(userid)
                .name(name)
                .cpf(document)
                .motherName(motherName)
                .login(login)
                .password(password)
                .build();

    }
}
