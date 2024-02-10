package com.jcCoder.springboottut.service;

import com.jcCoder.springboottut.entity.Department;
import com.jcCoder.springboottut.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        String departmentName="IT";
        String departmentAddress="BLR";
        String departmentCode="IT-02";
        Department department = Department.builder()
                .departmentName(departmentName)
                .departmentAddress(departmentAddress)
                .departmentCode(departmentCode).build();

        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("IT"))
                .thenReturn(department);


    }
@Test
@DisplayName("Get Department Name When the Department Name Validated ")
    public void whenValidDepartmentName_ThenDepartmentShouldFound(){
        String departmentName="IT";
        Department department = departmentService.getAllDepartmentName(departmentName);

        assertEquals(departmentName,department.getDepartmentName());

    }
}