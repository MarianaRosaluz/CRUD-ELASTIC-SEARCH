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
public class UserResponseDto {

    private Long userid;
    private  String name;
    private String cpf;

    public static UserResponseDto convert(User user){

        return UserResponseDto.builder()
                .userid(user.getUserid())
                .name(user.getName())
                .cpf(user.getCpf())
                .build();
    }

}
