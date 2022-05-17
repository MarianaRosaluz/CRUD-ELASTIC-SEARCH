package br.rosaluz.banking.system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User  {

    @Id
    @Field(type = FieldType.Keyword)
    private Long userid;

    @Transient
    @JsonIgnore
    @JsonIgnoreProperties
    private String index;

    @Field(type = FieldType.Keyword)
    private  String name;

    @Field(type = FieldType.Keyword)
    private String cpf;

    @Field(type = FieldType.Keyword)
    private Date birthDate;

    @Field(type = FieldType.Keyword)
    private String motherName;

    @Field(type = FieldType.Keyword)
    private  String login;

    @Field(type = FieldType.Keyword)
    private String  password;

}
