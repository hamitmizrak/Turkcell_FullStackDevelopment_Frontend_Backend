package com.hamitmizrak.bean;

import com.hamitmizrak.data.entity.EmployeeEntity;
import com.hamitmizrak.data.repository.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;
import java.util.UUID;

@Configuration
public class EmployeeEntityBean {

    @Autowired
    IEmployeeRepository repository;

    //kendi uniqu random sayı
    private int randomData(){
        Random random = new Random();
        int data = random.nextInt(300)+10;
       // System.out.println(data);
        return data;
    }

    @Bean
    public void employeeAllData(){
        for (int i = 1; i <=5 ; i++) {
            UUID uuid = UUID.randomUUID();
            EmployeeEntity employeeEntity= EmployeeEntity.builder().name("adı "+randomData()).surname("email "+randomData()). hescode(uuid.toString()).price(randomData()*100).price(randomData()*2).build();
            repository.save(employeeEntity);
        }
    }
}
