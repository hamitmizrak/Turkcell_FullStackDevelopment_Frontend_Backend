package com.hamitmizrak.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

//Dikkat: Buraya yazdığınız Bean mutlaka biryerde kullanın

@Configuration
public class PersistentTokenRepositoryBean {

    //import javax.activation.DataSource;
    @Autowired
    private DataSource dataSource;

    //Dikkat: bu metot adını ben yazmadım
    // Remember Me ==> PersistentTokenRepository
    //import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
    @Bean
    public PersistentTokenRepository persistentTokenRepositoryBeanMethod(){
        JdbcTokenRepositoryImpl tokenRepository=new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }
}
