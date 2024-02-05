package com.jcCoder.springboottut.controller;

import com.jcCoder.springboottut.entity.Department;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {
    @PostMapping("/department")
    public void saveDepartment( @RequestBody Department department){

    }
}
