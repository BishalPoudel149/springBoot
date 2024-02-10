package com.jcCoder.springboottut.repository;

import com.jcCoder.springboottut.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentName("Computer Engineering")
                .departmentAddress("London")
                .departmentCode("CS-IJ2")
                .build();

        entityManager.persist(department);
    }
     @Test
     @DisplayName("Confirming Department ID getID  if created ")
        public void DepartmentId_TestIfCreated(){
            Department department1=departmentRepository.findById(1L).get();

            assertEquals(department1.getDepartmentName(),"Computer Engineering");
        }







}