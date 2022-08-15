package com.hamitmizrak.data.repository;

import com.hamitmizrak.data.entity.EmployeeEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmployeeRepository extends PagingAndSortingRepository<EmployeeEntity,Long> {

    //delived Query
   // List<EmployeeEntity> findEmployeeEntitiesByEmployeeNameStartsWith(String name);


}
