package com.hamitmizrak.business.service;

import com.hamitmizrak.business.dto.UserRegisterDto;
import org.springframework.security.core.userdetails.UserDetailsService;

//UserDetailsService: Sistemdeki kullanıcı ile etkileşimde bulunmak için kullanıyoruz.
public interface IUserServices  extends UserDetailsService {

    //register için kaydetmekl
    public UserRegisterDto saveUserRegister(UserRegisterDto userRegisterDto);

}
