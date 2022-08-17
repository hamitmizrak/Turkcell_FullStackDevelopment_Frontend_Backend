package com.hamitmizrak.business.service;

import com.hamitmizrak.business.dto.UserRegisterDto;
import com.hamitmizrak.data.entity.UserRegisterEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

//UserDetailsService: Sistemdeki kullanıcı ile etkileşimde bulunmak için kullanıyoruz.
public interface IUserServices  extends UserDetailsService {

    //register için kaydetmekl
    UserRegisterDto saveUserRegister(UserRegisterDto userRegisterDto);
}
