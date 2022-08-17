package com.hamitmizrak.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

//Lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterDto {

    //Validation işlemleri yapılacak
    private Long id;

    @NotEmpty(message = "kullanıcı adını boş geçemezsiniz")
    private String name;

    @NotEmpty(message = "kullanıcı soyadını  boş geçemezsiniz")
    private String surname;

    @NotEmpty(message = "kullanıcı email geçemezsiniz")
    @Email(message = "uygun formatta girmediniz exam: deneme@xyz.com")
    private String email;

    // Hm51524
    @NotEmpty(message = "Kullanıcı şifresi boş geçilemez")
    @Size(min = 7, max = 50, message = "kullanıcı şifresi min:7 max:50 olabilir")
    @Pattern(regexp = "^[a-zA-Z0-9]{7}")
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
