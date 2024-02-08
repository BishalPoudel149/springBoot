package com.jcCoder.springboottut.service;

import com.jcCoder.springboottut.entity.Department;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    public Department saveDepartment(Department department);

    public List<Department> getAllDepartment();

    public Department getAllDepartmentName(String departmentName);

    public Department fetchByIdService(Long id);

    public List<Department> fetAllByAddressService(String address);

    public void deleteDepartmentService(Long id);

    public Department updateDepartment(Long id, Department department);
}
