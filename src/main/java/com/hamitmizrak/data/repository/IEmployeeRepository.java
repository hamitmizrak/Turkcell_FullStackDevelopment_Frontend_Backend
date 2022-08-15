package com.hamitmizrak.data.repository;

import com.hamitmizrak.data.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmployeeRepository extends JpaRepository<EmployeeEntity,Long> {

    //delived Query
   // List<EmployeeEntity> findEmployeeEntitiesByEmployeeNameStartsWith(String name);


}
