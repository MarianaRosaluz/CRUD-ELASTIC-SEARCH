package br.rosaluz.banking.system.model.filter;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchFilter {

    private String userId;
    private String name;
    private String cpf;

}
