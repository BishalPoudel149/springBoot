package com.jcCoder.springboottut.service;

import com.jcCoder.springboottut.entity.Department;
import com.jcCoder.springboottut.repository.DepartmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        String departmentName = "IT";
        String departmentAddress = "BLR";
        String departmentCode = "IT-02";
        Department department = Department.builder().departmentName(departmentName).departmentAddress(departmentAddress).departmentCode(departmentCode).build();

        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("IT")).thenReturn(department);

        Mockito.when(departmentRepository.save(Mockito.any(Department.class))).thenReturn(department);

        Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.ofNullable(department));


    }

    @Test
    @DisplayName("Get Department Name When the Department Name Validated ")
    public void whenValidDepartmentName_ThenDepartmentShouldFound() {
        String departmentName = "IT";
        Department department = departmentService.getAllDepartmentName(departmentName);
        assertEquals(departmentName, department.getDepartmentName());

    }

    @Test
    @DisplayName("Save the Department Details Service ")
    public void departmentService_DepartmentSave_ReturnDepartment() {

        String departmentName = "IT";
        String departmentAddress = "BLR";
        String departmentCode = "IT-02";
        Department department = Department.builder().departmentName(departmentName).departmentAddress(departmentAddress).departmentCode(departmentCode).build();        //Arragne department and make the call
        Department departmentResult = departmentService.saveDepartment(department);

        Assertions.assertNotNull(departmentResult);

        assertEquals("IT", departmentResult.getDepartmentName());
    }

    @Test
    @DisplayName("Delete the Department Details")
    public void departmentService_DepartmentDelete() {

        assertAll(() -> departmentService.deleteDepartmentService(1L));
    }


    @Test
    @DisplayName("Update the Department In Department Service")
    public void setDepartmentService_UpdateDepartment() {
        String departmentName = "IT";
        String departmentAddress = "Hyderabad";
        Department department = Department.builder().departmentName(departmentName).departmentAddress(departmentAddress).build();

        Department department1= departmentService.updateDepartment(1L,department);

        assertEquals("Hyderabad",department1.getDepartmentAddress());

        assertEquals("IT-02",department1.getDepartmentCode());
    }
}