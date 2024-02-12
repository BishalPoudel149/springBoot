package com.jcCoder.springboottut.controller;

import com.jcCoder.springboottut.entity.Department;
import com.jcCoder.springboottut.entity.Hod;
import com.jcCoder.springboottut.error.DepartmentNotFoundException;
import com.jcCoder.springboottut.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static javax.management.Query.value;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DepartmentController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    void setUp() throws DepartmentNotFoundException {
        Long hodId=1L;
        String HodName="Martin";
        String HodGender="M";

        Hod raghavaHod=new Hod(hodId,HodName,HodGender);
        department=Department.builder()
                .departmentName("Business")
                .departmentAddress("Florida")
                .departmentCode("BS-Fl1")
                .departmentEmailAddress("businessFLorida@gmail.com")
                .departmentId(1L)
                .raghavaHod(raghavaHod)
                .build();

        Mockito.when(departmentService.fetchByIdService(1L)).thenReturn(department);

    }

    @Test
    @DisplayName("Save Department Details")
    public void Controller_saveDepartmentTest() throws Exception {
/*        Department inputDepartment=Department.builder()
                .departmentName("Business")
                .departmentAddress("Florida")
                .departmentCode("BS-Fl1")
                .build();*/

        Mockito.when(departmentService.saveDepartment(Mockito.any(Department.class)))
                .thenReturn(department);

        mockMvc.perform(post("/department")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"departmentName\": \"Business\",\n" +
                        "    \"departmentAddress\":\"Florida\",\n" +
                        "    \"departmentCode\":\"BS-Fl1\",\n" +
                        "    \"emailAddress\":\"busstk@gmai.com\",\n" +
                        "    \"raghavaHod\":{\n" +
                        "        \"hodName\":\"Martin\",\n" +
                        "        \"hodGender\":\"M\"\n" +
                        "    }\n" +
                        "}\n"))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Fetch the Department Details by Id")
   public void fetchByIdTest() throws Exception {
        mockMvc.perform(get("/department/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}