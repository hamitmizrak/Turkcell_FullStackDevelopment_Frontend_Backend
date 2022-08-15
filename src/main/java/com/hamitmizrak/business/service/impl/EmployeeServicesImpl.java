package com.hamitmizrak.business.service.impl;

import com.hamitmizrak.business.dto.EmployeeDto;
import com.hamitmizrak.business.service.IEmployeeServices;
import com.hamitmizrak.data.entity.EmployeeEntity;
import com.hamitmizrak.data.repository.IEmployeeRepository;
import com.hamitmizrak.exception.ResourceNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.*;

@Transactional
@Service
@Log4j2
//İşin yükünü alan kısım
//Unutma: Null Pointer Exception almamak @RequestBody eklemelisiniz
public class EmployeeServicesImpl  implements IEmployeeServices {

    @Autowired
    IEmployeeRepository  repository;

    @Autowired
    ModelMapper modelMapper;

    //model mapper
    @Override
    public EmployeeDto EntityToDto(EmployeeEntity employeeEntity) {
        EmployeeDto dto=   modelMapper.map(employeeEntity,EmployeeDto.class);
        return dto;
    }

    //model mapper
    @Override
    public EmployeeEntity DtoToEntity(EmployeeDto employeeDto) {
        EmployeeEntity entity=  modelMapper.map(employeeDto,EmployeeEntity.class);
        return entity;
    }


    //SAVE
    //http://localhost:8080/save/employees
    @Override
    @PostMapping("/save/employees")
    public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeEntity entity  =  DtoToEntity(employeeDto);
        repository.save(entity);
        log.info("Employee Kaydedildi");
        return employeeDto;
    }


    //LIST
    //http://localhost:8080/list/employees
    @Override
    @GetMapping("/list/employees")
    public List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> list=new ArrayList<>();
        Iterable<EmployeeEntity> listem= repository.findAll();
        for(EmployeeEntity entity  : listem){
            EmployeeDto dto=   EntityToDto(entity);
            list.add(dto);
        }
        return list;
    }


    //FIND
    //filter:find
    //http://localhost:8080/find/employees/1
    @Override
    @GetMapping("/find/employees/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById( @PathVariable(name="id") Long id) throws Throwable {

        //1.YOL
        EmployeeEntity entity= repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee "+id+" id bulamadı !!!"));

        //2.YOL
        Optional<EmployeeEntity> findData = repository.findById(id);
        if (findData.isPresent()) {
            findData.get();
        } else {
        }

        //ModelMapper
        EmployeeDto dto=   EntityToDto(entity);
        return ResponseEntity.ok(dto);
    }

    //UPDATE
    //Update
    //http://localhost:8080/update/employees/1
    @Override
    @PutMapping("/update/employees/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(  @PathVariable(name="id")  Long id, @RequestBody EmployeeDto employeeDto) throws Throwable {

        //findEntity
        EmployeeEntity entityFind= repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee "+id+" id bulamadı !!!"));

        //DtoToEntity
        EmployeeEntity entity=   DtoToEntity(employeeDto);

        //setEntity
        entityFind.setName(entity.getName());
        entityFind.setSurname(entity.getSurname());
        entityFind.setPrice(entity.getPrice());

        EmployeeEntity entity2= repository.save(entityFind);
        //EntityToDto
        EmployeeDto dto=  EntityToDto(entity2);
        return  ResponseEntity.ok(dto);
    }

    //DELETE
    //Delete
    //http://localhost:8080/delete/employees/1
    @Override
    @DeleteMapping("/delete/employees/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable(name="id")  Long id) {
        //findEntity
        EmployeeEntity entityFind= repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee "+id+" id bulamadı !!!"));

        //Delete 2 seçeneği vardır
        //1-) deleteById(number);
        //1-) delete(object);
        repository.delete(entityFind);
        Map<String,Boolean> response=new HashMap<>();
        response.put("silindi",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}
