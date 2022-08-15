package com.hamitmizrak.ui.rest.impl;

import com.hamitmizrak.business.dto.EmployeeDto;
import com.hamitmizrak.business.service.IEmployeeServices;

import com.hamitmizrak.ui.rest.IEmployeeRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//http://localhost:8080/api/v1/
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1")
//dış dünyaya açılan kapı
public class EmployeeRest implements IEmployeeRest {

    @Autowired
    IEmployeeServices services;

    //ROOT
    //http://localhost:8080/api/v1
    //http://localhost:8080/api/v1/index
    @GetMapping({"/", "/index"})
    public String getRoot(){
        return "index";
    }


    //SAVE
    //http://localhost:8080/api/v1/employees
    @Override
    @PostMapping("/employees")
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
        services.createEmployee(employeeDto);
        return employeeDto;
    }


    //LIST
    //http://localhost:8080/api/v1/employees
    @Override
    @GetMapping("/employees")
    public List<EmployeeDto> getAllEmployees() {
       List<EmployeeDto> list= services.getAllEmployees();
        return list;
    }

    //FIND
    //http://localhost:8080/api/v1/employees/1
    @Override
    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable(name = "id")  Long id) throws Throwable {
        ResponseEntity<EmployeeDto> entity=services.getEmployeeById(id);
        return entity;
    }

    //UPDATE
    //http://localhost:8080/api/v1/employees/1
    @Override
    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable(name = "id")  Long id, @RequestBody EmployeeDto employeeDto) throws Throwable {
        services.updateEmployee(id,employeeDto);
        return ResponseEntity.ok(employeeDto);
    }

    //DELETE
    //http://localhost:8080/api/v1/employees/1
    @Override
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable(name = "id") Long id) {
        services.deleteEmployee(id);
        Map<String,Boolean> response=new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
