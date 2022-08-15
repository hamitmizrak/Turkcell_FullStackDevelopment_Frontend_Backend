package com.hamitmizrak;

import com.hamitmizrak.business.dto.EmployeeDto;
import com.hamitmizrak.data.entity.EmployeeEntity;
import com.hamitmizrak.data.repository.IEmployeeRepository;
import com.hamitmizrak.exception.ResourceNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


//test void yazarak devam edilir.
//test: birbirinden bağımsız yapılar için kullanıyoruz.
@SpringBootTest
@Log4j2
class TurkcellFullStackDevelopmentApplicationTests implements ITest {

    @Autowired
    IEmployeeRepository repository;


    @BeforeAll
    static void getBeforeAllMethod(){
        System.out.println("Bütün testtelerden önce gelirim");
        log.error("Bütün testtelerden önce gelirim");
    }

    @BeforeEach
    void getPreviousEmployeeBeforeEach(){
        System.out.println("Employee Testten hemen öncesi");
        log.error("Bütün testtelerden önce gelirim");
    }

    @Test
    @Override
    //testin amaçı: bu test database veri ekleyebiliyor mu ?
    public void testSave() {
        EmployeeEntity employeeEntity=EmployeeEntity.builder().name("hamit").surname("mızrak").price(1).hescode("hescode").build();
        repository.save(employeeEntity);
        //Test : eklenenen ik veriyi sistemde göstermek
        //eğer olmazsa yandaki hatayı döner: java.util.NoSuchElementException: No value present
        assertNotNull(repository.findById(6L).get());
    }


    @Test
    @Override
    //Biraz önce yazdığımız testSave bulabiliyor muyuz
    public void testFind() {
        EmployeeEntity employeeFind= repository.findById(6L).orElseThrow(()->new ResourceNotFoundException("id Bulunamadı"));
        //eğer olmazsa yandaki hatayı döner: java.util.NoSuchElementException: No value present
        //expected: Beklenen
        //actual: Gerçekte olan
        //assertEquals(short expected, short actual)
        //eğer beklenen ile gerçekte olan aynı ise
        // başarılıdır(Success)
        // değilse ==> Failed
        assertEquals("hamit",employeeFind.getName());
    }


    @Test
    @Override
    public void testList() {
        List<EmployeeEntity> list = repository.findAll();
        assertThat(list).size().isGreaterThan(0);
    }


    @Test
    @Override
    public void testUpdate() {
        EmployeeEntity employeeFind= repository.findById(1L).orElseThrow(()->new ResourceNotFoundException("id Bulunamadı"));
        employeeFind.setName("Malatya");
        repository.save(employeeFind);

        assertNotEquals("hamit",repository.findById(1L).get().getName());
    }


    @Test
    @Override
    public void testDelete() {
        repository.deleteById(1L);
        assertThat(repository.existsById(1L)).isFalse();
    }

    @AfterEach
    void getPreviousEmployeeAftereEach(){
        System.out.println("Employee Testten hemen sonrasında");
        log.error("Employee Testten hemen sonrasında");
    }

    @AfterAll
    static void getAfterAllMethod(){
        System.out.println("Employee Bütün testlerden hemen sonra  gelirim");
        log.error("Employee Bütün testlerden hemen sonra  gelirim");
    }

}
