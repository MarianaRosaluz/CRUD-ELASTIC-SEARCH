package br.rosaluz.banking.system.dto;

import br.rosaluz.banking.system.model.filter.UserSearchFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserSearchFilterDTO {

    private String id;
    private  String name;

    public UserSearchFilter convertToUserFilter(){
        return  UserSearchFilter.builder()
                .userId(id)
                .name(name)
                .build();
    }
}
