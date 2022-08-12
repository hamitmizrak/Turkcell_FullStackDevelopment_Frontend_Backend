package com.hamitmizrak.business.service.impl;

import com.hamitmizrak.business.dto.EmployeeDto;
import com.hamitmizrak.business.service.IEmployeeServices;
import com.hamitmizrak.data.entity.EmployeeEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class EmployeeServicesImpl  implements IEmployeeServices {


    //model mapper
    @Override
    public EmployeeDto entityToDto(EmployeeEntity employeeEntity) {
        return null;
    }

    //model mapper
    @Override
    public EmployeeEntity dtoToEntity(EmployeeDto employeeDto) {
        return null;
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        return null;
    }

    @Override
    public List<EmployeeDto> getAllEmployee(EmployeeDto employeeDto) {
        return null;
    }

    @Override
    public ResponseEntity<EmployeeDto> getEmployeeById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(Long id, EmployeeDto employeeDto) {
        return null;
    }

    @Override
    public ResponseEntity<EmployeeDto> deleteEmployee(Long id) {
        return null;
    }
}
