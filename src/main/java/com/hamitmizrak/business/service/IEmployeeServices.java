package com.hamitmizrak.business.service;

import com.hamitmizrak.business.dto.EmployeeDto;
import com.hamitmizrak.data.entity.EmployeeEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IEmployeeServices {

    //modelMapper: DTO  to Entity
    public EmployeeDto EntityToDto(EmployeeEntity employeeEntity);
    public EmployeeEntity DtoToEntity(EmployeeDto  employeeDto);

    //save
    public EmployeeDto createEmployee(EmployeeDto employeeDto);

    //list
    public List<EmployeeDto> getAllEmployees();

    //filter:find
    public ResponseEntity<EmployeeDto> getEmployeeById(Long id) throws Throwable;

    //update
    public ResponseEntity<EmployeeDto> updateEmployee(Long id,EmployeeDto employeeDto) throws Throwable;

    //delete
    public ResponseEntity<Map<String,Boolean>> deleteEmployee(Long id);

}
