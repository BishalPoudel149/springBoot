package com.jcCoder.springboottut.controller;

import com.jcCoder.springboottut.entity.Department;
import com.jcCoder.springboottut.service.DepartmentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {

    @Autowired
   private DepartmentService departmentService;

    Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @PostMapping("/department")
    public Department saveDepartment(@Valid @RequestBody Department department){
        logger.info("Department Has been included to save");
        return departmentService.saveDepartment(department);
    }
    @GetMapping("/department")
    public List<Department> getAllDeparment(){
        return departmentService.getAllDepartment();
    }

    @GetMapping("/department/{id}")
    public Department fetchById(@PathVariable("id") Long id) {
        return departmentService.fetchByIdService(id);
    }

    @GetMapping("/department/name/{name}")
    public Department getByDepartmentName( @PathVariable("name") String departmentName){
        return departmentService.getAllDepartmentName(departmentName);
    }

    @GetMapping("/department/address/{address}")

    public List<Department> fetByAllByaddress(@PathVariable("address") String departmentAddress){
        return departmentService.fetAllByAddressService(departmentAddress);
    }

    @DeleteMapping("/department/delete/{id}")
    public String deleteDepartment( @PathVariable("id") Long id){
        logger.info("Organization with id "+id+" Deleted");
        departmentService.deleteDepartmentService(id);
        return "Department Deleted Successfully";
    }

    @PutMapping("/department/update/{id}")
    public Department updateDepartment(@PathVariable("id") Long id, @RequestBody Department department){
        return departmentService.updateDepartment(id,department);
    }

}
