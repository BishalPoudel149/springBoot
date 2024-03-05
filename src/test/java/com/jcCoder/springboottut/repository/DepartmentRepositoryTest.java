package com.jcCoder.springboottut.repository;

import com.jcCoder.springboottut.entity.Department;
import org.junit.jupiter.api.Assertions;
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

        //Arrange
        Department department = Department.builder()
                .departmentName("Computer Engineering")
                .departmentAddress("London")
                .departmentCode("CS-IJ2")
                .departmentEmailAddress("hodcu@gmail.com")
                .build();

        entityManager.persist(department);
    }
     @Test
     @DisplayName("Confirming Department ID getID  if created ")
        public void DepartmentId_TestIfCreated(){

        //Act
            Department department1=departmentRepository.findById(1L).get();

            //Assert
            assertEquals(department1.getDepartmentName(),"Computer Engineering");
        }

        @Test
        @DisplayName("Save Department Using JPARespository")
        public void DepartmentRepository_SaveAll_ReturnDepartment(){

              //Arrange
            Department department = Department.builder()
                    .departmentName("IT Department")
                    .departmentAddress("KTM")
                    .departmentCode("IT-23")
                    .build();

            Department savedDepartment=departmentRepository.save(department);

            // Act
            Assertions.assertNotNull(savedDepartment);

            assertEquals("KTM",savedDepartment.getDepartmentAddress());

            // Assert

        }
         // For evaluating the Exception
        // assertThrows ( Exception.Class, Lamda Function)

    @Test
    @DisplayName("Find Department Name Repository Test")
     public void FindByDepartmentName_Test(){
        Department department=Department.builder()
                .departmentName("Business")
                .departmentAddress("London")
                .build();

        Department savedDepartment=departmentRepository.save(department);

        Department getDepartmentBYName = departmentRepository.findByDepartmentNameIgnoreCase(savedDepartment.getDepartmentName());

        assertEquals(getDepartmentBYName.getDepartmentName(),"Business");

     }


}