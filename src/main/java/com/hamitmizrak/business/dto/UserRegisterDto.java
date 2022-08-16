package com.hamitmizrak.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterDto {

    //Validation işlemleri yapılacak
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String picture="resim.png";
    private boolean isActive=false;

    //Parametreli constuctor
    public UserRegisterDto(String name, String surname, String email, String password, String picture, boolean isActive) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.picture = picture;
        this.isActive = isActive;
    }
}
