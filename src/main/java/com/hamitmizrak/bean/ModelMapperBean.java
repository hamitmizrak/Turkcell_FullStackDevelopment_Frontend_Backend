package com.hamitmizrak.bean;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperBean {

    @Bean
    //Entity ile Dto arasında birbirine çevirmek için kullanıyoruz
    public ModelMapper modelMapperMethod(){
        //dikkat: new ModelMapper() yapıyoruz yanlışlıkla Class ismini yazma
        return new ModelMapper();
    }
}
