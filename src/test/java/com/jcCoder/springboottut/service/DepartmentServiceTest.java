package com.jcCoder.springboottut.service;

import com.jcCoder.springboottut.entity.Department;
import com.jcCoder.springboottut.error.DepartmentNotFoundException;
import com.jcCoder.springboottut.repository.DepartmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
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
        Long departmentId = 1L;
        String departmentName = "IT";
        String departmentAddress = "BLR";
        String departmentCode = "IT-02";
        Department department = Department.builder()
                .departmentId(departmentId)
                .departmentName(departmentName)
                .departmentAddress(departmentAddress)
                .departmentCode(departmentCode)
                .build();

        List<Department> departmentList  = new ArrayList<>(2);
        departmentList.add(department);
        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("IT")).thenReturn(department);

        Mockito.when(departmentRepository.save(Mockito.any(Department.class))).thenReturn(department);

        Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.ofNullable(department));

        Mockito.when(departmentRepository.findAll()).thenReturn(departmentList);

    }

    @Test
    @DisplayName("Fetch Department With Valid ID")
    public void fetchDepartmentByValidId() throws DepartmentNotFoundException {
        Long departmentId = 1L;

        //calling the get Department by ID Service
        Department department = departmentService.fetchByIdService(departmentId);
        assertEquals(department.getDepartmentAddress(), "BLR");

    }

    @Test
    @DisplayName("Try to Fetch Department with Invalid Id")
    public void fetchDepartmentByInvalidIdAndReturnException(){
        assertThrows(DepartmentNotFoundException.class,() -> departmentService.fetchByIdService(2L));
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
    @DisplayName("Fetch All the Departments Present")
    public void getAllDepartmentValidTest(){
        List<Department> departmentList = departmentService.getAllDepartment();

        assertEquals(departmentList.get(0).getDepartmentId(),1L);
        assertEquals(departmentList.get(0).getDepartmentName(),"IT");


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

        Department department1 = departmentService.updateDepartment(1L, department);

        assertEquals("Hyderabad", department1.getDepartmentAddress());

        assertEquals("IT-02", department1.getDepartmentCode());
    }


}