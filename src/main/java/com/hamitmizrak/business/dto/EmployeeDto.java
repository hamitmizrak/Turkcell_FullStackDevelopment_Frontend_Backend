package com.hamitmizrak.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;

//lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
public class EmployeeDto implements Serializable {
    public static final Long serialVersionUID=1L;

    private Long id;
    private String name;
    private String surname;
    private double price;
    
    private String dtoCreatedDate=nowDate();

    private String nowDate() {
        return null;
    }

}
