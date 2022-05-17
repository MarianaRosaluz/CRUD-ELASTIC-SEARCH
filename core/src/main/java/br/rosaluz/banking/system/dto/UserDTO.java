package br.rosaluz.banking.system.dto;

import br.rosaluz.banking.system.model.User;

public class UserDTO {

    private  String name;
    private String document;
    private String motherName;
    private  String  login;
    private String  password;



    public UserDTO(String name, String document, String motherName, String login, String password) {
        this.name = name;
        this.document = document;
        this.motherName = motherName;
        this.login = login;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public User convertToUser(){
        return  User.builder()
                .name(name)
                .document(document)
                .motherName(motherName)
                .login(login)
                .password(password)
                .build();

    }
}
