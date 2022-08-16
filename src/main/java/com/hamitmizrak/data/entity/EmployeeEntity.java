package com.hamitmizrak.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

//lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2


//Entity
@Entity
@Table(name="employee")
public class EmployeeEntity  extends  BaseEntity implements Serializable {
    public static final Long serialVersionUID =1L;

    private String  name;
    private String  surname;
    private String  hescode;
    private double price;
}
