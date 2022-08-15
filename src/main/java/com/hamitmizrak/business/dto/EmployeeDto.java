package com.hamitmizrak.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

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
    private String hescode;
    private double price;
    
//    //private String dtoCreatedDate=nowDate();
//
//    private String nowDate() {
//        Locale locale=new Locale("tr","TR");
//        EmployeeDto employeeDto=new EmployeeDto()
//        return employeeDto.nowLlocalDate();
//        return employeeDto.nowCalendarLocale();
//    }
//
//    // date Turkish time
//    public  String nowCalendarLocale() {
//        return new SimpleDateFormat("dd-MMMM-yyyy HH:mm:ss ",  new Locale("tr", "TR")).format(Calendar.getInstance().getTime());
//    }
//
//    public  String nowLlocalDate() {
//        return DateTimeFormatter.ofPattern("dd/MMMM/yyyy HH:mm:ss").format(LocalDateTime.now());
//    }

}
